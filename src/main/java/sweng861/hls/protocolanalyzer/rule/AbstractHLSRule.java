package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractHLSRule implements HLSRule {
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, 0);
		file.addValidationError(entry);
	}

}
