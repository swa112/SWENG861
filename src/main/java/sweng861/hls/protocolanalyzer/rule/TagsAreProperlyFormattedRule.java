package sweng861.hls.protocolanalyzer.rule;

import java.util.List;

import sweng861.hls.protocolanalyzer.HLSUtility;
import sweng861.hls.protocolanalyzer.annotation.ProtocolCompatibility;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileEntity;
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
class TagsAreProperlyFormattedRule extends AbstractMediaFileRule {

	private static final char TAG_SEPARATOR = ':';
	private static final String ATTRIBUTE_SEPARATOR = ",";
	private static final String NAME_VALUE_SEPARATOR = "=";


	/**
	 * @see HLSRule#runRuleCheck(HLSMediaFile, HLSMediaFileLineInfo)
	 */
	public void runRuleCheck(HLSMediaFile file) {

			List<HLSMediaFileLineInfo> fileLines = file.getFileLines();
			for (HLSMediaFileLineInfo fileLine : fileLines){
				MediaFileTagType lineType = fileLine.getLineType();
				if(lineType.isEvaluateTag()){
					String lineData = fileLine.getLineData();
					checkForProtocolCompatibility(lineType, file, fileLine);
					
					int index = lineData.indexOf(TAG_SEPARATOR);
					if(index != -1){ 
						String tagValue = lineData.substring(index + 1);
						if(lineType.isTagProperlyFormatted(tagValue, file.getVersion())){
							if(lineType.getValueDataType().equals(MediaFileTagValueDataType.ATTRIBUTE_LIST)){
								checkTagAttributes(lineType, tagValue, file, fileLine);
							}
						}else {
							super.addToErrorLog(
									file, 
									ErrorType.TAG_FORMAT_ERROR, 
									String.format(ErrorType.TAG_FORMAT_ERROR.getMessageFormat(), lineType.toString(), tagValue), 
									fileLine.getLineNumber());
						}
					}else {
						super.addToErrorLog(
								file, 
								ErrorType.TAG_MISSING_COLON, 
								String.format(ErrorType.TAG_MISSING_COLON.getMessageFormat(), lineType.toString()), 
								fileLine.getLineNumber());
					}
				
				}
			}
		}

	
	private void checkTagAttributes(MediaFileTagType type, String tagValue, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){

		String[] attributes = tagValue.split(ATTRIBUTE_SEPARATOR);

		for (String attribute : attributes){
			String[] nameValue = attribute.split(NAME_VALUE_SEPARATOR); //length should be 2. 
			MediaFileTagAttributeType attributeType = MediaFileTagAttributeType.getAttributeTypeFromString(nameValue[0].trim());
			
			checkForProtocolCompatibility(attributeType, file, lineInfo);
			
			if(!attributeType.equals(MediaFileTagAttributeType.NOT_FOUND)){
				if (!attributeType.isAttributeValueProperlyFormatted(nameValue[1])){
					super.addToErrorLog(
							file, 
							ErrorType.INVALID_ATTRIBUTE_DATA_TYPE, 
							String.format(ErrorType.INVALID_ATTRIBUTE_DATA_TYPE.getMessageFormat(), attributeType.toString(), tagValue),
							lineInfo.getLineNumber() );
				}
			}else {
				super.addToErrorLog(
						file, 
						ErrorType.ATTRIBUTE_NOT_FOUND,
						String.format(ErrorType.ATTRIBUTE_NOT_FOUND.getMessageFormat(), nameValue[0], lineInfo.getLineType().toString()), 
						lineInfo.getLineNumber() );
			}

	
		}


	}
	
	private void checkForProtocolCompatibility(Enum<? extends HLSMediaFileEntity> type, HLSMediaFile file, HLSMediaFileLineInfo lineInfo){

		ProtocolCompatibility protocolCompatibilityIndicator = null;
		try {
			protocolCompatibilityIndicator = type.getClass().getField(type.name()).getAnnotation(ProtocolCompatibility.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		if(protocolCompatibilityIndicator !=null){
			int version = file.getVersion();
			int compatibleVersion = protocolCompatibilityIndicator.version();
			int deprecatedVersion = protocolCompatibilityIndicator.deprecatedAsOf();
			MediaFileTagValueDataType dataType = protocolCompatibilityIndicator.compatibleDataType();
			
			if(dataType.equals(MediaFileTagValueDataType.NONE)){
				if(compatibleVersion > version){
					addToErrorLog(file, 
							ErrorType.INCOMPATIBLE_VERSION,
							String.format(ErrorType.INCOMPATIBLE_VERSION.getMessageFormat(), type.toString(), version),
							lineInfo.getLineNumber());
				}
				if(version > deprecatedVersion){
					addToErrorLog(file, 
							ErrorType.USE_OF_DEPRECATED_PROTOCOL,
							String.format(ErrorType.USE_OF_DEPRECATED_PROTOCOL.getMessageFormat(), type.toString(), protocolCompatibilityIndicator.deprecatedAsOf()),
							lineInfo.getLineNumber());
				}
			}else if (version >= compatibleVersion){
					String tagValue = HLSUtility.getTagValue(lineInfo.getLineData());
					if(!tagValue.matches(dataType.getDataTypeRegEx())){
						addToErrorLog(file, 
								ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE,
								String.format(ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE.getMessageFormat(),lineInfo.getLineType().toString(), tagValue),
								lineInfo.getLineNumber());
					}
				
			}
		}
		
	}

}
