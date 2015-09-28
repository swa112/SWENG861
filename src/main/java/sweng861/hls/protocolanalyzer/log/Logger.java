/**
 * 
 */
package sweng861.hls.protocolanalyzer.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileType;

/**
 * @author Scott
 *
 */
public class Logger extends Thread {

	private static final String LOG_FORMAT = "%d, %s, File: %s, line: %s, message: %s,";
	
	private static final String APPLICATION = "APPLICATION";
	
	private MediaStreamAnalyzerResult result; 
	
	public Logger(MediaStreamAnalyzerResult result){
		this.result = result;
	}
		
	public void run() {
			try {
				long currentTime = System.currentTimeMillis();
				Date currentDate = new Date(currentTime);
				String fileName = getMasterPlaylistName(result);
				SimpleDateFormat dateformat = new SimpleDateFormat("MMddyy");
				String logFile = dateformat.format(currentDate).concat("_").concat(fileName).concat(".csv");
				File log = new File("C:\\Users\\Scott\\Documents\\PSU Software Engineering\\Fall 2015\\SWENG861\\workspace\\protocal-analyzer\\logs\\" + logFile);
//				File log = new File("V:\\protocol-analyzer\\SWENG861\\logs\\" + logFile);
				FileWriter filewriter = new FileWriter(log);
				BufferedWriter writer = new BufferedWriter(filewriter);
				writer.write("Number, Error, File, Line Number, Message");
				writer.newLine();
				List<ErrorLogEntry> allErrors = result.getErrors();
				int counter = 0;
				for (ErrorLogEntry entry : allErrors){
					writer.write(String.format(LOG_FORMAT, counter, entry.getError().name(), APPLICATION, entry.getLineNumber(), entry.getMessage()));
					writer.newLine();
					counter++;
				}
				List<HLSMediaFile> files = result.getFiles();
				for (HLSMediaFile file : files){
					List<ErrorLogEntry> validationErrors = file.getValidationErrors();
					for (ErrorLogEntry entry : validationErrors){
						writer.write(String.format(LOG_FORMAT, counter, entry.getError().name(), file.getFileName(), entry.getLineNumber(), entry.getMessage()));
						writer.newLine();
						counter++;
					}
				}
				
				writer.close();
				filewriter.close();
			}catch(IOException io){
				io.printStackTrace();
			}
		}
	
	private String getMasterPlaylistName(MediaStreamAnalyzerResult result){
		List<HLSMediaFile> files = result.getFiles();
		for (HLSMediaFile file : files){
			if(file.getFileType().equals(MediaFileType.MASTER_PLAYLIST)){
				int start = file.getFileName().lastIndexOf(HLSConstants.SLASH);
				int end = file.getFileName().lastIndexOf(HLSConstants.DOT);
				return file.getFileName().substring(start + 1, end);
			}
		}
		return null;
	}

}
