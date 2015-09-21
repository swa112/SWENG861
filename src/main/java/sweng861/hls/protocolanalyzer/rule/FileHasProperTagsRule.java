package sweng861.hls.protocolanalyzer.rule;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;

 class FileHasProperTagsRule extends AbstractMediaFileRule {
	 

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileTagType startTag = file.getFileType().getStartTag();
		MediaFileTagType[] requiredTags = file.getFileType().getRequiredTags();
		MediaFileTagType[] optionalTags = file.getFileType().getOptionalTags();
		final Set<MediaFileTagType> tagSet = new HashSet<MediaFileTagType>(file.getTagList());
		//Validate start tag
		if(!file.getFileLines().get(0).getLineType().equals(startTag)){
			super.addToErrorLog(file, 
					ErrorType.MISSING_START_TAG,
					String.format(ErrorType.MISSING_START_TAG.getMessageFormat(), file.getFileType().toString(), startTag));
		}
		//Evaluate required tags	
		for (MediaFileTagType requiredTag : requiredTags){
			if (!tagSet.contains(requiredTag)){
				super.addToErrorLog(file, 
						ErrorType.MISSING_REQUIRED_TAG, 
						String.format(ErrorType.MISSING_REQUIRED_TAG.getMessageFormat(), requiredTag.toString(), file.getFileType().name()));
			}
		}
		//Evaluate allowed tags (must be in required and optional sets)
		Collection<MediaFileTagType> invalidTags = tagSet;
		invalidTags.removeAll(Arrays.asList(optionalTags));
		invalidTags.removeAll(Arrays.asList(requiredTags));
		if (!invalidTags.isEmpty()){
			for (MediaFileTagType tag : invalidTags){
				if(!tag.equals(MediaFileTagType.NOT_A_TAG)){ //Improper tags are handled in a different rule. 
					super.addToErrorLog(file, 
							ErrorType.INVALID_TAG_FOR_FILE, 
							String.format(ErrorType.INVALID_TAG_FOR_FILE.getMessageFormat(), tag.toString(), file.getFileType().name()));
				}
			}	
			
		}

	}



}
