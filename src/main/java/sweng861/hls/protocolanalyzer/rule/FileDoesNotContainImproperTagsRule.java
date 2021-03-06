/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;

/**
 * @author Scott
 *
 */
class FileDoesNotContainImproperTagsRule extends AbstractMediaFileRule{

	public void runRuleCheck(HLSMediaFile file) {
		
		for (HLSMediaFileLineInfo line : file.getFileLines()) {
			if (line.getLineType().equals(MediaFileTagType.NOT_A_TAG)){
				super.addToErrorLog(file, 
						ErrorType.FOUND_IMPROPER_TAG, 
						String.format(ErrorType.FOUND_IMPROPER_TAG.getMessageFormat(), line.getLineData()), 
						line.getLineNumber());
			}
		}

	}
}
