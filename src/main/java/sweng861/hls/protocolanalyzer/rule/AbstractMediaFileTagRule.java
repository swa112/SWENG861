package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorSeverityType;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public abstract class AbstractMediaFileTagRule implements HLSRule {


	
	public void runRuleCheck(HLSMediaFile file){
		throw new UnsupportedOperationException(""); //TODO
	}
	
	
	protected void addToErrorLog(HLSMediaFile file, ErrorType errorType, String message, int lineNumber){
		ErrorLogEntry entry = new ErrorLogEntry(
				errorType, file.getFileName(), message, lineNumber);
		file.addValidationError(entry);
	}

}
