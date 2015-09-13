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
 class EvaluateCommentsToFindIncorrectTags extends AbstractMediaFileRule{

	public void runRuleCheck(HLSMediaFile file) {
		if (file.getTagList().contains(MediaFileTagType.COMMENT)){
			for (HLSMediaFileLineInfo line : file.getFileLines()){
				if (line.getLineType().equals(MediaFileTagType.COMMENT)){
					super.addToErrorLog(file, 
							ErrorType.COMMENT_FOUND.getSeverity(), 
							String.format(ErrorType.COMMENT_FOUND.getMessageFormat(), line.getLineData(), line.getLineNumber()));
				}
			}
		}
		
	}
	 
	 
	
	

}
