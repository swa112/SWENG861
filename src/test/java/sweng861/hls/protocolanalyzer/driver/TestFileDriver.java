/**
 * 
 */
package sweng861.hls.protocolanalyzer.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.LogUtility;
import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.validator.MediaFileValidator;
import sweng861.hls.protocolanalyzer.validator.Validator;

/**
 * @author Scott
 *
 */
public class TestFileDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<HLSMediaFile> fileList = new ArrayList<HLSMediaFile>();
		MediaStreamAnalyzerResult result = new MediaStreamAnalyzerResult();
		result.setFiles(fileList);
		String directory = "C:\\Users\\Scott\\Documents\\PSU Software Engineering\\Fall 2015\\SWENG861\\workspace\\protocal-analyzer";
		File master = new File(directory + "\\testFiles\\ipad.m3u8");
		File high = new File (directory + "\\testFiles\\ipadipad-high.m3u8");
		File med = new File (directory +"\\testFiles\\ipadipad-med.m3u8");
		File low = new File (directory + "\\testFiles\\ipadipad-low.m3u8");
		File [] files = new File[] {master, high, med, low};
		
		for (File file : files) {
			HLSMediaFile mediaFile = new HLSMediaFile(file.getName());
			
			
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader); 
				LineNumberReader lineNumberReader  = new LineNumberReader(reader);
				String line = "";
	
				do {
					line = lineNumberReader.readLine();
					
					if (line != null) {
						MediaFileTagType lineType = MediaFileTagType.findTagByLine(line);
						HLSMediaFileLineInfo lineInfo = new HLSMediaFileLineInfo();
						lineInfo.setLineData(line);
						lineInfo.setLineNumber(lineNumberReader.getLineNumber());
						lineInfo.setLineType(lineType);
						mediaFile.addFileLine(lineInfo);
					
					}
	//				
				}while (line != null);
				
				//After processing file, determine it's type. 
				MediaFileType fileType = MediaFileType.matchFileTypeOnIdentifyingTag(mediaFile.getFileLines());
				mediaFile.setFileType(fileType);
				result.getFiles().add(mediaFile); 
				lineNumberReader.close();
				reader.close();
				fileReader.close();
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		
		Validator validator = new MediaFileValidator();
		validator.validate(result.getFiles());
		result.setFiles(result.getFiles());
		try {
			LogUtility.writeToLog(result);
		}catch (IOException io){
			io.printStackTrace();
		}
	}

}
