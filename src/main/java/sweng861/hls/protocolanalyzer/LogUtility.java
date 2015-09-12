/**
 * 
 */
package sweng861.hls.protocolanalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;

/**
 * @author Scott
 *
 */
public final class LogUtility {
	
	private static final String LOG_FORMAT = "%s: File: %s, line: %s, message: %s \n";
	
	private static final String APPLICATION = "APPLICATION";
	
	public static void writeToLog(MediaStreamAnalyzerResult result) throws IOException{
		long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);
		SimpleDateFormat format = new SimpleDateFormat("MMddyy");
		String logFile = format.format(currentDate).concat(".log");
		File log = new File("\\logs\\" + logFile);
		FileWriter filewriter = new FileWriter(log);
		BufferedWriter writer = new BufferedWriter(filewriter);
		List<ValidationErrorLogEntry> allErrors = result.getErrors();
		for (ValidationErrorLogEntry entry : allErrors){
			writer.write(String.format(LOG_FORMAT,  entry.getErrorType().name(), APPLICATION, entry.getLineNumber(), entry.getMessage()));
		}
		List<HLSMediaFile> files = result.getFiles();
		for (HLSMediaFile file : files){
			List<ValidationErrorLogEntry> validationErrors = file.getValidationErrors();
			for (ValidationErrorLogEntry entry : validationErrors){
				writer.write(String.format(LOG_FORMAT, entry.getErrorType().name(), file.getFileName(),  entry.getLineNumber(), entry.getMessage()));
			
			}
		}
		
		writer.close();
	}

}
