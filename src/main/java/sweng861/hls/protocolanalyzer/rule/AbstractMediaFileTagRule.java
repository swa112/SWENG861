package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractMediaFileTagRule implements HLSRule {

	public void runRuleCheck(HLSMediaEntity mediaEntity) {
		this.runRuleCheck((HLSMediaFileLineInfo) mediaEntity);
	}

	public boolean handlesRequest(HLSMediaEntity mediaEntity) {
		return this.handlesRequest((HLSMediaFileLineInfo) mediaEntity);
	}
	
	protected abstract void runRuleCheck(HLSMediaFileLineInfo fileLine);
	
	protected  boolean handlesRequest(HLSMediaFileLineInfo fileLine) {
		return true;
	}
	
	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message, int lineNum){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, lineNum);
		file.addValidationError(entry);
	}
	

}
