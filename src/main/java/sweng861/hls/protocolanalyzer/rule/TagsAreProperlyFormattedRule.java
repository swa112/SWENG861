package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.annotation.DeprecatedProtocol;
import sweng861.hls.protocolanalyzer.evaluator.ErrorSeverityType;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagAttributeType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType;

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


	/**
	 * @see HLSRule#runRuleCheck(HLSMediaFile, HLSMediaFileLineInfo)
	 */
	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo fileLine) {

		MediaFileTagType lineType = fileLine.getLineType();
		String lineData = fileLine.getLineData();
		checkForDeprecatedProtocol(lineType, file, fileLine);
		
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
						ErrorType.TAG_FORMAT_ERROR, 
						String.format(ErrorType.TAG_FORMAT_ERROR.getMessageFormat(), lineType.name(), tagValue), 
						fileLine.getLineNumber());
			}
		}else {
			super.addToErrorLog(
					file, 
					ErrorType.TAG_MISSING_COLON, 
					String.format(ErrorType.TAG_MISSING_COLON.getMessageFormat(), lineType.name()), 
					fileLine.getLineNumber());
		}




		// Get media tag and determine data type
		//parse the tag after the ":", and run a match on the data type regex (includes attribute list)
		//if the data type is an attribute list, enforce that the required attributes are present
		//also enforce that only the allowed attributes are found. 
		//For each attribute, parse on the = and , to get the value and send it through the data type validator. 
		//If the data type is an enumerated string, the enum validation should handle enforcing that the value is one of the enumerated strings. 

	}
	private void checkTagAttributes(MediaFileTagType type, String tagValue, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){

		String[] attributes = tagValue.split(ATTRIBUTE_SEPARATOR);
		//Need a list of attributes to do the required and allowed checks. 
		for (String attribute : attributes){
			String[] nameValue = attribute.split(NAME_VALUE_SEPARATOR); //length should be 2. 
			MediaFileTagAttributeType attributeType = MediaFileTagAttributeType.getAttributeTypeFromString(nameValue[0].trim());
			
			checkForDeprecatedProtocol(attributeType, file, lineInfo);
			
			if(!attributeType.equals(MediaFileTagAttributeType.NOT_FOUND)){
				if (!attributeType.isAttributeValueProperlyFormatted(nameValue[1])){
					super.addToErrorLog(
							file, 
							ErrorType.INVALID_ATTRIBUTE_DATA_TYPE, 
							String.format(ErrorType.INVALID_ATTRIBUTE_DATA_TYPE.getMessageFormat(), attributeType.name(), tagValue),
							lineInfo.getLineNumber() );
				}
			}else {
				super.addToErrorLog(
						file, 
						ErrorType.ATTRIBUTE_NOT_FOUND,
						String.format(ErrorType.ATTRIBUTE_NOT_FOUND.getMessageFormat(), nameValue[0], lineInfo.getLineType().name()), 
						lineInfo.getLineNumber() );
			}

			//get AttributeName
			//validate data type for attribute
			//check required attributes
			//check allowable attributes
		}


	}
	
	private void checkForDeprecatedProtocol(Enum attributeType, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){
		
		DeprecatedProtocol deprecatedIndicator = null;
		try {
			deprecatedIndicator = attributeType.getClass().getField(attributeType.name()).getAnnotation(DeprecatedProtocol.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		if(deprecatedIndicator !=null){
			super.addToErrorLog(file, 
					ErrorType.USE_OF_DEPRECATED_PROTOCOL,
					String.format(ErrorType.USE_OF_DEPRECATED_PROTOCOL.getMessageFormat(), attributeType.name(), deprecatedIndicator.asOf()), 
					lineInfo.getLineNumber());
		}
	}

}
