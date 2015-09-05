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

public class HLSMediaFileServiceImpl implements HLSMediaFileService{
	
	
	public List<HLSMediaFile> analyzeFiles(String urlStr) throws MalformedURLException, IOException {
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		parseAndCreateFiles(urlStr, fileList);
		return fileList;
	}
	
	private void parseAndCreateFiles(String urlStr, List<HLSMediaFile> fileList) throws IOException {
			HLSMediaFile file = new HLSMediaFile(urlStr);
			fileList.add(file);
			int lastIndex = urlStr.lastIndexOf('/');
			String baseUrl = urlStr.substring(0, lastIndex+1);

			
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
					
					file.addFileLine(line);
						
					if (line!=null && !line.matches(HLSConstants.TAG_PATTERN)){
						
						String nextURL = baseUrl.concat(line);
						
						//Line should be URL
						if (!line.matches(HLSConstants.TS_FILE_PATTERN)){
							parseAndCreateFiles(nextURL, fileList);
						}
					}
					
					
				}while (line != null);
			}catch(MalformedURLException e){
				String messageFormat = "URL [%s] was not found or found invalid text in the file.";
				ValidationErrorLogEntry entry = new ValidationErrorLogEntry(ValidationErrorSeverityType.WARNING, String.format(messageFormat, urlStr));
				file.addValidationError(entry);
			}
		
		}


	

}
