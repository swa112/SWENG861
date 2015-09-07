package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

 class TagsAreProperlyFormattedRule extends AbstractMediaFileTagRule {
	
	 private static final char TAG_SEPARATOR = ':';
	private static final String TAG_FORMAT_ERROR = "Tag [%s] is not formatted with the proper data type";
	private static final String TAG_MISSING_COLON = "Could not determine data type for tag [%s] because : could not be found";
	
	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo fileLine) {
		
		MediaFileTagType lineType = fileLine.getLineType();
		if(lineType.isEvaluateTag()){
			String lineData = fileLine.getLineData();
			int splitAt = lineData.indexOf(TAG_SEPARATOR);
			if(splitAt != -1){ 
				String tagValue = lineData.substring(splitAt + 1);
				if(! lineType.isTagProperlyFormatted(tagValue)){
					super.addToErrorLog(
							file, ValidationErrorSeverityType.FATAL, String.format(TAG_FORMAT_ERROR, lineType.name()), fileLine.getLineNumber());
				}
			}else {
				super.addToErrorLog(
						file, ValidationErrorSeverityType.FATAL, String.format(TAG_MISSING_COLON, lineType.name()), fileLine.getLineNumber());
			}
				
		}
		// Get media tag and determine data type
		//parse the tag after the ":", and run a match on the data type regex (includes attribute list)
		//if the data type is an attribute list, enforce that the required attributes are present
		//also enforce that only the allowed attributes are found. 
		//For each attribute, parse on the = and , to get the value and send it through the data type validator. 
		//If the data type is an enumerated string, the enum validation should handle enforcing that the value is one of the enumerated strings. 
		
	}

}
