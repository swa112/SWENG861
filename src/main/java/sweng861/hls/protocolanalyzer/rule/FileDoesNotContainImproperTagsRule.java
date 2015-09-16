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
class FileDoesNotContainImproperTagsRule extends AbstractMediaFileTagRule{

	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo line) {

		if (line.getLineType().equals(MediaFileTagType.NOT_A_TAG)){
			super.addToErrorLog(file, 
					ErrorType.FOUND_IMPROPER_TAG.getSeverity(), 
					String.format(ErrorType.FOUND_IMPROPER_TAG.getMessageFormat(), line.getLineData()), 
					line.getLineNumber());
		}



	}





}
