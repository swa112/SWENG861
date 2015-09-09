package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractMediaFileTagRule implements HLSRule {


	
	public void runRuleCheck(HLSMediaFile file){
		throw new UnsupportedOperationException(""); //TODO
	}
	
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message, int lineNumber){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, lineNumber);
		file.addValidationError(entry);
	}

}
