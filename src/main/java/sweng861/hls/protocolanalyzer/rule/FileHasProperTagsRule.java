package sweng861.hls.protocolanalyzer.rule;

import java.util.Arrays;
import java.util.Collection;


import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

 class FileHasProperTagsRule implements HLSRule {

	public void runRuleCheck(HLSMediaFile file) {
		MediaFileTagType[] requiredTags = file.getFileType().getRequiredTags();
		MediaFileTagType[] allowedTags = file.getFileType().getAllowedTags();
		//TODO - restructure to check each required tag so we can report on what tag is missing. 
		if (!file.getTagSet().containsAll(Arrays.asList(requiredTags))){
			ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
					ValidationErrorSeverityType.FATAL, "A required tag is missing.", 0);
			file.addValidationError(entry);
		}
		Collection<MediaFileTagType> invalidTags = file.getTagSet();
		invalidTags.removeAll(Arrays.asList(allowedTags));
		if (!invalidTags.isEmpty()){
			//could add a loop to print out invalid tags
			ValidationErrorLogEntry entry = new ValidationErrorLogEntry(
					ValidationErrorSeverityType.FATAL, "Found tag that is not allowed in file.", 0);
			file.addValidationError(entry);
		}
	
		// TODO: Make sure tags that must only appear once are only in the tag list once.

	}

	public boolean handlesRequest(HLSMediaFile file) {
		// TODO Auto-generated method stub
		return false;
	}

}
