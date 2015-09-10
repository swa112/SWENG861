package sweng861.hls.protocolanalyzer.rule;

import java.util.Arrays;
import java.util.Collection;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

 class FileHasProperTagsRule extends AbstractMediaFileRule {
	 
	private static final String MISSING_REQUIRED_TAG_FORMAT = "Required Tag [%s] was not found";
	private static final String INVALID_TAG_FORMAT = "Found tag [%s] that is not allowed in file";

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileTagType startTag = file.getFileType().getStartTag();
		MediaFileTagType[] requiredTags = file.getFileType().getRequiredTags();
		MediaFileTagType[] optionalTags = file.getFileType().getOptionalTags();
		
		if(!file.getFileLines().get(0).getLineType().equals(startTag)){
			super.addToErrorLog(file, ValidationErrorSeverityType.FATAL, "Expected start tag did not match.");
		}
			
		for (MediaFileTagType requiredTag : requiredTags){
			if (!file.getTagSet().contains(requiredTag)){
				super.addToErrorLog(file, ValidationErrorSeverityType.FATAL, String.format(MISSING_REQUIRED_TAG_FORMAT, requiredTag.name()));
			}
		}
		
		Collection<MediaFileTagType> invalidTags = file.getTagSet();
		invalidTags.removeAll(Arrays.asList(optionalTags));
		invalidTags.removeAll(Arrays.asList(requiredTags));
		if (!invalidTags.isEmpty()){
			for (MediaFileTagType tag : invalidTags){
				super.addToErrorLog(file, ValidationErrorSeverityType.FATAL, String.format(INVALID_TAG_FORMAT, tag.name()));
			}
		}
	
		// TODO: Make sure tags that must only appear once are only in the tag list once.

	}



}
