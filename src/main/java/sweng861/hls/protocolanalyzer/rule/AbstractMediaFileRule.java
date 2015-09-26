package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public abstract class AbstractMediaFileRule implements HLSRule {



	protected void addToErrorLog(HLSMediaFile file, ErrorType error, String message){
		ErrorLogEntry entry = new ErrorLogEntry(
				error, file.getFileName(), message, HLSConstants.FILE_LEVEL);
		file.addValidationError(entry);
	}
	
	protected void addToErrorLog(HLSMediaFile file, ErrorType error, String message, int lineNumber){
		ErrorLogEntry entry = new ErrorLogEntry(
				error, file.getFileName(), message, String.valueOf(lineNumber));
		file.addValidationError(entry);
	}


}
