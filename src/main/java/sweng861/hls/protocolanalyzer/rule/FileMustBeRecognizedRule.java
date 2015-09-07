package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

 class FileMustBeRecognizedRule extends AbstractMediaFileRule{

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileType fileType = file.getFileType();
		if (fileType == null || fileType.equals(MediaFileType.INVALID_FILE)){
			super.addToErrorLog(file, ValidationErrorSeverityType.FATAL, "An invalid file type was found.");
		}
	}


}
