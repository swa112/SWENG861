package sweng861.hls.protocolanalyzer.rule;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

class TagsMustBeInProperSequenceRule extends AbstractMediaFileRule {
	
	private static final String MISSING_FOLLOWING_TAG = "The tag [%s] is not followed by the required type. ";

	public void runRuleCheck(HLSMediaFile file) {
		
		List<HLSMediaFileLineInfo> fileLines = file.getFileLines();
		for (int i=0; i< fileLines.size(); i++){
			if(i != fileLines.size()-1){
				HLSMediaFileLineInfo hlsMediaFileLineInfo = fileLines.get(i);
				MediaFileTagType nextNonCommentLine = this.getNextNonCommentLine(fileLines, i+1);
				if (!hlsMediaFileLineInfo.getLineType().isTagFollowedByRequiredType(nextNonCommentLine)){
					super.addToErrorLog(file, 
							ValidationErrorSeverityType.FATAL,
							String.format(MISSING_FOLLOWING_TAG, hlsMediaFileLineInfo.getLineType().name()),
							hlsMediaFileLineInfo.getLineNumber());
				}
			}
		}

	}
	
	private MediaFileTagType getNextNonCommentLine(List<HLSMediaFileLineInfo> fileLines, int startIndex){
		MediaFileTagType nextTag = fileLines.get(startIndex).getLineType();
		while(nextTag.equals(MediaFileTagType.COMMENT)){
			startIndex++;
			if(startIndex <= fileLines.size()-1){
				nextTag = fileLines.get(startIndex).getLineType();
			}
		}
		return nextTag;
	}

}
