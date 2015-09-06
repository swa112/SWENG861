package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

 class FileMustBeRecognizedRule implements HLSRule{

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileType fileType = file.getFileType();
		if (fileType == null || fileType.equals(MediaFileType.INVALID_FILE)){
			
			ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
					ValidationErrorSeverityType.FATAL, "An invalid file type was found.", 0);
			file.addValidationError(entry);
		}
	}

	public boolean handlesRequest(HLSMediaFile file) {
		// TODO Auto-generated method stub
		return false;
	}

}
