package sweng861.hls.protocolanalyzer.rule;

import java.util.List;

import sweng861.hls.protocolanalyzer.annotation.FollowedBy;
import sweng861.hls.protocolanalyzer.evaluator.ErrorSeverityType;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;

class TagsMustBeInProperSequenceRule extends AbstractMediaFileRule {
	
	public void runRuleCheck(HLSMediaFile file) {
		
		List<HLSMediaFileLineInfo> fileLines = file.getFileLines();
		for (int i=0; i< fileLines.size(); i++){
			if(i != fileLines.size()-1){
				HLSMediaFileLineInfo hlsMediaFileLineInfo = fileLines.get(i);
				MediaFileTagType nextNonCommentLine = this.getNextNonCommentLine(fileLines, i+1);
				if (!isTagFollowedByRequiredType(hlsMediaFileLineInfo.getLineType(), nextNonCommentLine)){
					super.addToErrorLog(file, 
							ErrorType.MISSING_FOLLOWING_TAG.getSeverity(),
							String.format(ErrorType.MISSING_FOLLOWING_TAG.getMessageFormat(), hlsMediaFileLineInfo.getLineType().name()),
							hlsMediaFileLineInfo.getLineNumber());
				}
			}
		}

	}
	
	private MediaFileTagType getNextNonCommentLine(List<HLSMediaFileLineInfo> fileLines, int startIndex){
		MediaFileTagType nextTag = fileLines.get(startIndex).getLineType();
		while(nextTag.equals(MediaFileTagType.COMMENT) && startIndex < fileLines.size()-1){
			startIndex++;
			nextTag = fileLines.get(startIndex).getLineType();
			
		}
		return nextTag;
	}
	
	public boolean isTagFollowedByRequiredType(MediaFileTagType currentTag, MediaFileTagType typeForNextLine){
		
		FollowedBy followedBy = null;
		
		try {
			followedBy = currentTag.getClass().getField(currentTag.name()).getAnnotation(FollowedBy.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		if (followedBy != null){
			MediaFileTagType[] values = followedBy.value();
			for(MediaFileTagType type : values){
				if (type.equals(typeForNextLine)){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	


}
