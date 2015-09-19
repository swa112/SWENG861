package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileType;

 class FileMustBeRecognizedRule extends AbstractMediaFileRule{

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileType fileType = file.getFileType();
		if (fileType == null || fileType.equals(MediaFileType.INVALID_FILE)){
			super.addToErrorLog(file, 
					ErrorType.INVALID_FILE_TYPE, 
					String.format(ErrorType.INVALID_FILE_TYPE.getMessageFormat(), file.getFileName()));
		}
	}


}
