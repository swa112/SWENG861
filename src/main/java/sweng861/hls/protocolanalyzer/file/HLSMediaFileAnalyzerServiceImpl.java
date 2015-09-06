package sweng861.hls.protocolanalyzer.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.validator.MediaFileValidator;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;
import sweng861.hls.protocolanalyzer.validator.Validator;

//@Singleton
//@Path("singleton-bean")
public class HLSMediaFileAnalyzerServiceImpl implements HLSMediaFileAnalyzerService{
	
	private static final int LINE_START = 1; 
	
	public List<HLSMediaFile> analyzeFiles(String urlStr) throws MalformedURLException, IOException {
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		processFiles(urlStr, fileList);
		Validator validator = new MediaFileValidator();
		validator.validate(fileList);
		return fileList;
	}
	
	private void processFiles(String urlStr, List<HLSMediaFile> fileList) throws IOException {
			HLSMediaFile file = new HLSMediaFile(urlStr);
			fileList.add(file);
			int lastIndex = urlStr.lastIndexOf('/');
			String baseUrl = urlStr.substring(0, lastIndex+1); //need to determine if relative or absolute

			try {
				BufferedReader reader = getFileReader(urlStr);
				String line = "";
				int lineNum = LINE_START;
				do {
					line = reader.readLine();
					
					if (line != null) {
						MediaFileTagType lineType = MediaFileTagType.findTagByLine(line);
						HLSMediaFileLineInfo lineInfo = new HLSMediaFileLineInfo();
						lineInfo.setLineData(line);
						lineInfo.setLineNumber(lineNum);
						lineInfo.setLineType(lineType);
						file.addFileLine(lineInfo);
						if(lineType.isURI()){
							
							if (!lineType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
								String nextURL = this.getNextURL(line, baseUrl, lineType);
								processFiles(nextURL, fileList);
							}
						}
					}
					lineNum++;
				}while (line != null);
				
				//After processing file, determine it's type. 
				MediaFileType fileType = MediaFileType.matchFileTypOnIdentifyingTag(file.getFileLines());
				file.setFileType(fileType);
				
			}catch(MalformedURLException e){
				String messageFormat = "URL [%s] was not found or found invalid text in the file.";
				ValidationErrorLogEntry entry = new ValidationErrorLogEntry(ValidationErrorSeverityType.WARNING, String.format(messageFormat, urlStr), 0);
				file.addValidationError(entry);
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
		InputStream inStream = (InputStream)connection.getContent();
		InputStreamReader inStreamReader = new InputStreamReader(inStream);
		BufferedReader reader = new BufferedReader(inStreamReader);
		return reader;
	}


	

}
