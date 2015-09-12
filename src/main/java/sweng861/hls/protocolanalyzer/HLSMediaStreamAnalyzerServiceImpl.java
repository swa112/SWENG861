package sweng861.hls.protocolanalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.validator.MediaFileValidator;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;
import sweng861.hls.protocolanalyzer.validator.Validator;

//@Singleton
//@Path("singleton-bean")
 
public class HLSMediaStreamAnalyzerServiceImpl implements HLSMediaStreamAnalyzerService{
	

	
	public MediaStreamAnalyzerResult analyzeFiles(String urlStr) throws MalformedURLException, IOException {
		MediaStreamAnalyzerResult result = new MediaStreamAnalyzerResult();
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		result.setFiles(fileList);
		processFiles(urlStr, result);
		Validator validator = new MediaFileValidator();
		validator.validate(fileList);
		result.setFiles(fileList);
		LogUtility.writeToLog(result);
		return result;
	}
	
	private void processFiles(String urlStr, MediaStreamAnalyzerResult result){
			HLSMediaFile file = new HLSMediaFile(urlStr);
			int lastIndex = urlStr.lastIndexOf('/');
			String baseUrl = urlStr.substring(0, lastIndex+1); 
			BufferedReader reader; 
			LineNumberReader lineNumberReader;
			try {
				reader = getFileReader(urlStr);
				lineNumberReader = new LineNumberReader(reader);
				String line = "";

				do {
					line = lineNumberReader.readLine();
					
					if (line != null) {
						MediaFileTagType lineType = MediaFileTagType.findTagByLine(line);
						HLSMediaFileLineInfo lineInfo = new HLSMediaFileLineInfo();
						lineInfo.setLineData(line);
						lineInfo.setLineNumber(lineNumberReader.getLineNumber());
						lineInfo.setLineType(lineType);
						file.addFileLine(lineInfo);
						if(lineType.isURI() && !lineType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
							String nextURL = this.getNextURL(line, baseUrl, lineType);
							processFiles(nextURL, result);
						}
					}
//					
				}while (line != null);
				
				//After processing file, determine it's type. 
				MediaFileType fileType = MediaFileType.matchFileTypeOnIdentifyingTag(file.getFileLines());
				file.setFileType(fileType);
				result.getFiles().add(file); 
				lineNumberReader.close();
				reader.close();
			}catch(MalformedURLException e){
				String messageFormat = "URL [%s] was not found or found invalid text in the file.";
				ValidationErrorLogEntry entry = new ValidationErrorLogEntry(ValidationErrorSeverityType.WARNING, String.format(messageFormat, urlStr), 0);
				result.addError(entry);
			}catch (IOException e){
				String messageFormat = "URL [%s] was not found or found invalid text in the file.";
				ValidationErrorLogEntry entry = new ValidationErrorLogEntry(ValidationErrorSeverityType.WARNING, String.format(messageFormat, urlStr), 0);
				result.addError(entry);
			}
		
		}
	
	private String getNextURL(String uri, String baseUrl,  MediaFileTagType uriType){
		if(uriType.equals(MediaFileTagType.RELATIVE_PLAYLIST_URI)){
			return baseUrl.concat(uri);
		}
		return uri;
	}

	private BufferedReader getFileReader(String urlStr) throws MalformedURLException,
			IOException {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.connect();
//		connection.getHeaderField(arg0);
		//TODO getHeaders to verify rule.
		InputStream inStream = (InputStream)connection.getContent();
		InputStreamReader inStreamReader = new InputStreamReader(inStream);
		BufferedReader reader = new BufferedReader(inStreamReader);
		return reader;
	}
	
	private void getSocketConnection()throws IOException{
		Socket socket = new Socket("", 21);
	}
	
//	private void writeToLog(MediaStreamAnalyzerResult result) throws IOException{
//		long currentTime = System.currentTimeMillis();
//		Date currentDate = new Date(currentTime);
//		SimpleDateFormat format = new SimpleDateFormat("MMddyy");
//		String logFile = format.format(currentDate).concat(".log");
//		File log = new File("C:\\Users\\Scott\\Documents\\PSU Software Engineering\\Fall 2015\\SWENG861\\workspace\\protocal-analyzer\\logs\\" + logFile);
//		FileWriter filewriter = new FileWriter(log);
//		BufferedWriter writer = new BufferedWriter(filewriter);
//		List<ValidationErrorLogEntry> allErrors = result.getErrors();
//		for (ValidationErrorLogEntry entry : allErrors){
//			writer.write(String.format(LOG_FORMAT,  entry.getErrorType().name(), APPLICATION, entry.getLineNumber(), entry.getMessage()));
//		}
//		List<HLSMediaFile> files = result.getFiles();
//		for (HLSMediaFile file : files){
//			List<ValidationErrorLogEntry> validationErrors = file.getValidationErrors();
//			for (ValidationErrorLogEntry entry : validationErrors){
//				writer.write(String.format(LOG_FORMAT, entry.getErrorType().name(), file.getFileName(),  entry.getLineNumber(), entry.getMessage()));
//			
//			}
//		}
//		
//		writer.close();
//	}
	

	public static void main (String [] args){

		URL url = null; 
		try {
			new URL("" );
			URLConnection connection = url.openConnection();
			connection.connect();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	

}
