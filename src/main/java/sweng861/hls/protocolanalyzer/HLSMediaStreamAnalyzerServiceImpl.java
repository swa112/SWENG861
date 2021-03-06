package sweng861.hls.protocolanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.evaluator.Evaluator;
import sweng861.hls.protocolanalyzer.evaluator.HLSRuleEvaluator;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.file.TransportStreamFileInfo;
import sweng861.hls.protocolanalyzer.log.Logger;

import com.sun.research.ws.wadl.HTTPMethods;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;

 
public class HLSMediaStreamAnalyzerServiceImpl implements HLSMediaStreamAnalyzerService{
	
//	private final static String [] allowedContentHeaders = new String []{
//		"application/mpegurl", 
//		"audio/x-mpegurl", 
//		"application/vnd.apple.mpegurl"};
	
	public MediaStreamAnalyzerResult analyzeFiles(String urlStr, boolean isAnalyzeTS) throws MalformedURLException, IOException {
		MediaStreamAnalyzerResult result = new MediaStreamAnalyzerResult();
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		result.setFiles(fileList);
		processFiles(urlStr, result, isAnalyzeTS);
		Evaluator evaluator = new HLSRuleEvaluator();
		evaluator.evaluate(fileList);
		result.setFiles(fileList);
		Logger logger = new Logger(result);
		logger.start();
		return result;
	}
	
	private void processFiles(String urlStr, MediaStreamAnalyzerResult result, boolean isAnalyzeTS){
			HLSMediaFile file = new HLSMediaFile(urlStr);
			int lastIndex = urlStr.lastIndexOf('/');
			String baseUrl = urlStr.substring(0, lastIndex+1); 
			InputStreamReader inStreamReader = null;
			BufferedReader reader = null; 
			LineNumberReader lineNumberReader = null;
			try {
				URLConnection connection = getConnection(urlStr, result);
				InputStream inStream = (InputStream)connection.getContent();
				inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
				reader = new BufferedReader(inStreamReader);
				lineNumberReader = new LineNumberReader(reader);
				String line = "";

				do {
					line = lineNumberReader.readLine();
					
					if (line != null && !line.trim().isEmpty()) {
						MediaFileTagType lineType = MediaFileTagType.findTagByLine(line);
						HLSMediaFileLineInfo lineInfo = new HLSMediaFileLineInfo();
						lineInfo.setLineData(line);
						lineInfo.setLineNumber(lineNumberReader.getLineNumber());
						lineInfo.setLineType(lineType);
						if(lineType.equals(MediaFileTagType.EXT_X_VERSION)){
							file.setVersion(Integer.parseInt(HLSUtility.getTagValue(line)));
						}
						file.addFileLine(lineInfo);
						if(lineType.isURI() && !lineType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
							String nextURL = this.getNextURL(line, baseUrl, lineType);
							processFiles(nextURL, result, isAnalyzeTS);
						}else if (lineType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
							//verify TS file exists
							String tsURL = this.getNextURL(line, baseUrl, lineType);
							try {

								URL url = new URL(tsURL);
								HttpURLConnection tsConnection = (HttpURLConnection) url.openConnection();
								tsConnection.setRequestMethod(HTTPMethods.HEAD.value());
								tsConnection.connect();
								int responseCode = tsConnection.getResponseCode();
								if(responseCode == HttpURLConnection.HTTP_NOT_FOUND){
									logURIError(tsURL, result, file);
								} else if (isAnalyzeTS){
//								
									analyzeTransportStream(file, tsURL);
								}
//							
							}catch(MalformedURLException e){
								logURIError(tsURL, result, file);
							}catch (IOException e){
								logURIError(tsURL, result, file);
							}						
						}
					}
//					
				} while (line != null);
				
				//After processing file, determine it's type. 
				MediaFileType fileType = MediaFileType.matchFileTypeOnIdentifyingTag(file.getFileLines());
				file.setFileType(fileType);
				result.getFiles().add(file); 
			
			}catch(MalformedURLException e){
				logURIError(urlStr, result, file);
			}catch (IOException e){
				logURIError(urlStr, result, file);
			}
			finally {
				try {
					if (inStreamReader != null){
						inStreamReader.close();
					}
					if (lineNumberReader != null){
						lineNumberReader.close();
					}
					if (lineNumberReader != null){
						reader.close();
					}
				}catch (IOException io) {
					System.err.println("Unable to close readers!");
				}
			}
		
		}

	private void analyzeTransportStream(HLSMediaFile file, String tsURL) {
		System.out.println(tsURL);
		IContainer container = IContainer.make();
		IContainerFormat format = IContainerFormat.make();
		format.setInputFormat(".ts");

		int returnCode = container.open(tsURL,IContainer.Type.READ, format);
		if(returnCode >= 0){
			
			BigDecimal bitRate = BigDecimal.valueOf(container.getBitRate());
			BigDecimal duration = BigDecimal.valueOf(container.getDuration());
			BigDecimal inSeconds = duration.divide(BigDecimal.valueOf(1000000));
			System.out.println(bitRate);
			System.out.println(inSeconds.toString());
			TransportStreamFileInfo tsFileInfo = new TransportStreamFileInfo();
			tsFileInfo.setDuration(inSeconds);
			tsFileInfo.setCalculatedBitRate(bitRate);
			tsFileInfo.setFileName(tsURL);
			file.addTsFiles(tsFileInfo);
		}
		container.close();
	}
	
	private void logURIError(String url, MediaStreamAnalyzerResult result, HLSMediaFile file){
		String message = String.format(ErrorType.INVALID_URI_FOUND.getMessageFormat(), url);
		ErrorLogEntry entry = new ErrorLogEntry(ErrorType.INVALID_URI_FOUND, file != null?file.getFileName():HLSConstants.APPLICATION,  message, HLSConstants.FILE_LEVEL);
		result.addError(entry);
	}
	
	private String getNextURL(String uri, String baseUrl,  MediaFileTagType uriType){
		if(uriType.equals(MediaFileTagType.RELATIVE_PLAYLIST_URI) || uriType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
			return baseUrl.concat(uri);
		}
		return uri;
	}

	
	private URLConnection getConnection(String urlStr, MediaStreamAnalyzerResult result) throws MalformedURLException, IOException {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.connect();

		
		return connection;
	}
			
}
