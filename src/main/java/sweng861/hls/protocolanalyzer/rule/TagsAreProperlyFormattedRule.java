package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.annotation.DeprecatedFileEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagAttributeType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorType;

/**
 * This rule enforces that the tags with expected values are properly set, i.e. if the tag expects an integer we validate that the tag value is an INT. 
 * If the tag value is not set or the tag is missing a colon to parse the tag from it's value, an error will be set for the file.  
 * @author Scott
 *
 */
class TagsAreProperlyFormattedRule extends AbstractMediaFileTagRule {

	private static final char TAG_SEPARATOR = ':';
	private static final String ATTRIBUTE_SEPARATOR = ",";
	private static final String NAME_VALUE_SEPARATOR = "=";
	private static final String TAG_FORMAT_ERROR = "Tag [%s] is not formatted with the proper data type. Value = [%s]";
	private static final String TAG_MISSING_COLON = "Could not determine data type for tag [%s] because : could not be found";
	private static final String ATTRIBUTE_DATA_TYPE = "Attribue [%s] data type does not match. Value = [%s]";
	private static final String ATTRIBUTE_NOT_FOUND = "Attribue [%s] was not recognized for tag [%s]";
	private static final String DEPRECATED_TAG_FOUND = "Found use of a deprecated tag: %s";

	/**
	 * @see HLSRule#runRuleCheck(HLSMediaFile, HLSMediaFileLineInfo)
	 */
	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo fileLine) {

		MediaFileTagType lineType = fileLine.getLineType();
		String lineData = fileLine.getLineData();
		checkForDeprecatedTag(lineType, file, fileLine);
		
		int index = lineData.indexOf(TAG_SEPARATOR);
		if(index != -1){ 
			String tagValue = lineData.substring(index + 1);
			if(lineType.isTagProperlyFormatted(tagValue)){
				if(lineType.getValueDataType().equals(MediaFileTagValueDataType.ATTRIBUTE_LIST)){
					checkTagAttributes(lineType, tagValue, file, fileLine);
				}
			}else {
				super.addToErrorLog(
						file, 
						ValidationErrorSeverityType.FATAL, 
						String.format(TAG_FORMAT_ERROR, lineType.name(), tagValue), 
						fileLine.getLineNumber());
			}
		}else {
			super.addToErrorLog(
					file, 
					ValidationErrorSeverityType.FATAL, 
					String.format(TAG_MISSING_COLON, lineType.name()), 
					fileLine.getLineNumber());
		}




		// Get media tag and determine data type
		//parse the tag after the ":", and run a match on the data type regex (includes attribute list)
		//if the data type is an attribute list, enforce that the required attributes are present
		//also enforce that only the allowed attributes are found. 
		//For each attribute, parse on the = and , to get the value and send it through the data type validator. 
		//If the data type is an enumerated string, the enum validation should handle enforcing that the value is one of the enumerated strings. 

	}
	
	private void checkForDeprecatedTag(MediaFileTagType type, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){
		if(type.isDeprecated()){
			super.addToErrorLog(file, ValidationErrorSeverityType.INFO, String.format(DEPRECATED_TAG_FOUND, type.name()), lineInfo.getLineNumber());
		}
	}

	private void checkTagAttributes(MediaFileTagType type, String tagValue, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){

		String[] attributes = tagValue.split(ATTRIBUTE_SEPARATOR);
		//Need a list of attributes to do the required and allowed checks. 
		for (String attribute : attributes){
			String[] nameValue = attribute.split(NAME_VALUE_SEPARATOR); //length should be 2. 
			MediaFileTagAttributeType attributeType = MediaFileTagAttributeType.getAttributeTypeFromString(nameValue[0].trim());
			
			checkForDeprecatedEntity(attributeType, file, lineInfo);
			
			if(!attributeType.equals(MediaFileTagAttributeType.NOT_FOUND)){
				if (!attributeType.isAttributeValueProperlyFormatted(nameValue[1])){
					super.addToErrorLog(
							file, 
							ValidationErrorSeverityType.FATAL, 
							String.format(ATTRIBUTE_DATA_TYPE, attributeType.name(), tagValue),
							lineInfo.getLineNumber() );
				}
			}else {
				super.addToErrorLog(
						file, 
						ValidationErrorSeverityType.FATAL,
						String.format(ATTRIBUTE_NOT_FOUND, nameValue[0], lineInfo.getLineType().name()), 
						lineInfo.getLineNumber() );
			}

			//get AttributeName
			//validate data type for attribute
			//check required attributes
			//check allowable attributes
		}


	}
	
	private void checkForDeprecatedEntity(MediaFileTagAttributeType attributeType, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){
		
		DeprecatedFileEntity deprecatedIndicator = null;
		try {
			deprecatedIndicator = attributeType.getClass().getField(attributeType.name()).getAnnotation(DeprecatedFileEntity.class);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(deprecatedIndicator !=null){
			super.addToErrorLog(file, 
					ValidationErrorType.USE_OF_DEPRECATED_ATTRIBUTE.getSeverity(),
					String.format(ValidationErrorType.USE_OF_DEPRECATED_ATTRIBUTE.getMessageFormat(), attributeType.name(), deprecatedIndicator.asOf()), 
					lineInfo.getLineNumber());
		}
	}

}
