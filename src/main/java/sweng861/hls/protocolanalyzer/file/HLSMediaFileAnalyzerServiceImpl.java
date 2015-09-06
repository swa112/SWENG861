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
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

//@Singleton
//@Path("singleton-bean")
public class HLSMediaFileAnalyzerServiceImpl implements HLSMediaFileAnalyzerService{
	
	
	public List<HLSMediaFile> analyzeFiles(String urlStr) throws MalformedURLException, IOException {
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		processFiles(urlStr, fileList);
		return fileList;
	}
	
	private void processFiles(String urlStr, List<HLSMediaFile> fileList) throws IOException {
			HLSMediaFile file = new HLSMediaFile(urlStr);
			fileList.add(file);
			int lastIndex = urlStr.lastIndexOf('/');
			String baseUrl = urlStr.substring(0, lastIndex+1); //need to determine if relative or absolute

			
			URL url = null;
			try {
				BufferedReader reader = getFileReader(urlStr);
				String line = "";
				int lineNum = 1;
				do {
					line = reader.readLine();
					
					if (line != null) {
						MediaFileTagType lineType = MediaFileTagType.findTagByLine(line);
						HLSMediaFileLineInfo lineInfo = new HLSMediaFileLineInfo();
						lineInfo.setLineData(line);
						lineInfo.setLineNumber(lineNum);
						lineInfo.setLineType(lineType);
						//need to add line info to file object
						file.addFileLine(lineInfo);
						//If line isn't a tag, it should be URI.	
//						if (!line.matches(HLSConstants.TAG_PATTERN)){
						if(lineType.isURI()){
							
							if (!lineType.equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
								String nextURL = this.getNextURL(line, baseUrl, lineType);
								processFiles(nextURL, fileList);
							}
						}
//						} else {
//							MediaFileTagType tagType = MediaFileTagType.findTagByLine(line);
//						}
					}
					lineNum++;
				}while (line != null);
				
				MediaFileType fileType = MediaFileType.matchFileTypOnIdentifyingTag(file.getFileLines());
				file.setFileType(fileType);
				if (fileType.equals(MediaFileType.INVALID_FILE)){
				
					ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
							ValidationErrorSeverityType.FATAL, "An invalid file type was found.", 0);
					file.addValidationError(entry);
				}
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
		URL url;
		url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream inStream = (InputStream)connection.getContent();
		InputStreamReader inStreamReader = new InputStreamReader(inStream);
		BufferedReader reader = new BufferedReader(inStreamReader);
		return reader;
	}


	

}
