package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

public abstract class AbstractMediaFileRule implements HLSRule {

	private static final int FILE_LEVEL = 0;

	public void runRuleCheck(HLSMediaEntity mediaEntity){
		//TODO add validation
		this.runRuleCheck((HLSMediaFile)mediaEntity);
	}

	public boolean handlesRequest(HLSMediaEntity mediaEntity) {
		//TODO add validation
		return handlesRequest((HLSMediaFile)mediaEntity);
	}

	protected abstract void runRuleCheck(HLSMediaFile file);


	protected boolean handlesRequest(HLSMediaFile file) {
		return true;
	}

	protected void addToErrorLog(HLSMediaFile file, ValidationErrorSeverityType severity, String message){
		ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
				severity, message, FILE_LEVEL);
		file.addValidationError(entry);
	}

}
