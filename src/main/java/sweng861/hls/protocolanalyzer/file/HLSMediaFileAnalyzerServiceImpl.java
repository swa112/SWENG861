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

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

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
				url = new URL(urlStr);
				URLConnection connection = url.openConnection();
				connection.connect();
				InputStream inStream = (InputStream)connection.getContent();
				InputStreamReader inStreamReader = new InputStreamReader(inStream);
				BufferedReader reader = new BufferedReader(inStreamReader);
				String line = "";
				do {
					line = reader.readLine();
					
					if (line != null) {
						file.addFileLine(line);
							
						if (!line.matches(HLSConstants.TAG_PATTERN)){
							
							String nextURL = baseUrl.concat(line);
							
							//Line should be URL
							if (!line.matches(HLSConstants.TS_FILE_PATTERN)){
								processFiles(nextURL, fileList);
							}
						}
					}
					
				}while (line != null);
				
				MediaFileType fileType = MediaFileType.matchFileTypOnIdentifyingTag(file.getFileLines());
				file.setFileType(fileType);
				if (fileType.equals(MediaFileType.INVALID_FILE)){
				
					ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
							ValidationErrorSeverityType.FATAL, "An invalid file type was found.");
					file.addValidationError(entry);
				}
			}catch(MalformedURLException e){
				String messageFormat = "URL [%s] was not found or found invalid text in the file.";
				ValidationErrorLogEntry entry = new ValidationErrorLogEntry(ValidationErrorSeverityType.WARNING, String.format(messageFormat, urlStr));
				file.addValidationError(entry);
			}
		
		}


	

}
