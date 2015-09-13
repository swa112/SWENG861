package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorSeverityType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;

public abstract class AbstractMediaFileRule implements HLSRule {

	private static final int FILE_LEVEL = 0;

	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo lineInfo){
		throw new UnsupportedOperationException();//TODO
	}

	protected void addToErrorLog(HLSMediaFile file, ErrorSeverityType severity, String message){
		addToErrorLog(file, severity, message, FILE_LEVEL);
	}
	
	protected void addToErrorLog(HLSMediaFile file, ErrorSeverityType severity, String message, int lineNumber){
		ErrorLogEntry entry = new ErrorLogEntry(
				severity, message, lineNumber);
		file.addValidationError(entry);
	}
	

}
