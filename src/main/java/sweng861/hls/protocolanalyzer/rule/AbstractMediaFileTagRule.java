package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractMediaFileTagRule implements HLSRule {

	private static final int FILE_LEVEL = 0;
	
	public void runRuleCheck(HLSMediaFile file){
		throw new UnsupportedOperationException(""); //TODO
	}
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message, int lineNum){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, lineNum);
		file.addValidationError(entry);
	}
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, FILE_LEVEL);
		file.addValidationError(entry);
	}

}
