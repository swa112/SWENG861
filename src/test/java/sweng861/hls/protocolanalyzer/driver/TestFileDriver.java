/**
 * 
 */
package sweng861.hls.protocolanalyzer.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.evaluator.Evaluator;
import sweng861.hls.protocolanalyzer.evaluator.MediaFileEvaluator;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.log.Logger;

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
		String directory = "C:\\Users\\Scott\\Documents\\PSU Software Engineering\\Fall 2015\\SWENG861\\workspace\\protocal-analyzer\\testFiles2";
		File master = new File(directory + "\\ipad.m3u8");
		File high = new File (directory + "\\ipadipad-high.m3u8");
		File med = new File (directory +"\\ipadipad-med.m3u8");
		File low = new File (directory + "\\ipadipad-low.m3u8");
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
		
		Evaluator validator = new MediaFileEvaluator();
		validator.validate(result.getFiles());
		result.setFiles(result.getFiles());
		Logger logger = new Logger(result);
		logger.run();

		System.out.println("Test Driver ended. Check logs for results");
	}

}
