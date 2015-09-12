package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractMediaFileRule implements HLSRule {

	private static final int FILE_LEVEL = 0;

	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo lineInfo){
		throw new UnsupportedOperationException();//TODO
	}

	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message){
		addToErrorLog(file, severity, message, FILE_LEVEL);
	}
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message, int lineNumber){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, lineNumber);
		file.addValidationError(entry);
	}
	

}
