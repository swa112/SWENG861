package sweng861.hls.protocolanalyzer.file;

import java.util.Arrays;

import sweng861.hls.protocolanalyzer.annotation.DeprecatedFileEntity;

public enum MediaFileTagAttributeType {
	
	BANDWIDTH ("BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	AVERAGE_BANDWITH ("AVERAGE-BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	CODECS("CODECS", MediaFileTagValueDataType.QUOTED_STRING),
	
	RESOLUTION("RESOLUTION", MediaFileTagValueDataType.DECIMAL_RESOLUTION),
	
	AUDIO("AUDIO", MediaFileTagValueDataType.QUOTED_STRING),
	
	VIDEO("VIDEO", MediaFileTagValueDataType.QUOTED_STRING),
	
	SUBTITLES("SUBTITLES", MediaFileTagValueDataType.QUOTED_STRING),
	
	CLOSED_CAPTIONS("CLOSED-CAPTINS", MediaFileTagValueDataType.QUOTED_STRING),
	
	TYPE("TYPE", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			String[] allowedValues = new String []{"AUDIO", "VIDEO", "SUBTITLES", "CLOSED-CAPTIONS"};
			if (Arrays.asList(allowedValues).contains(anAttributeDataValue)){
				return true; 
			}
			return false; 
		}
	},
	
	URI("URI", MediaFileTagValueDataType.QUOTED_STRING),
	
	GROUP_ID("GROUP-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	LANGUAGE("LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	ASSOC_LANGUAGE("ASSOC-LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	NAME("NAME", MediaFileTagValueDataType.QUOTED_STRING),
	
	DEFAULT("DEFAULT", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			return isAllowedValueYesNo(anAttributeDataValue);
		}
	},
	
	AUTO_SELECT("AUTO-SELECT", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			return isAllowedValueYesNo(anAttributeDataValue);
		}
	},
	
	FORCED("FORCED", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			return isAllowedValueYesNo(anAttributeDataValue);
		}
	},
	
	INSTREAM_ID("INSTREAM-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	CHARACTERISTICS("CHARACTERISTICS", MediaFileTagValueDataType.QUOTED_STRING),
	
	TIME_OFFSET("TIME-OFFSET", MediaFileTagValueDataType.SIGNED_DECIMAL_FLOATING_POINT), 
	
	PRECISE("PRECISE", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			return isAllowedValueYesNo(anAttributeDataValue);
		}
	},
	
	DATA_ID ("DATA-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	VALUE("VALUE", MediaFileTagValueDataType.QUOTED_STRING),
	
	METHOD("METHOD", MediaFileTagValueDataType.ENUMERATED_STRING){
		@Override
		protected boolean isAllowedValue(String anAttributeDataValue){
			String[] allowedValues = new String []{"NONE", "AES-128", "SAMPLE-AES"};
			if (Arrays.asList(allowedValues).contains(anAttributeDataValue)){
				return true; 
			}
			return false; 
		}
	},
	
	IV("IV", MediaFileTagValueDataType.HEXADECIMAL_SEQUENCE),
	
	KEY_FORMAT("KEYFORMAT", MediaFileTagValueDataType.QUOTED_STRING),
	
	KEY_FORMAT_VERSIONS("KEYFORMATVERSIONS", MediaFileTagValueDataType.QUOTED_STRING),
	
	BYTE_RANGE("BYTERANGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	@DeprecatedFileEntity(asOf="version 6")
	PROGRAM_ID("PROGRAM-ID", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	NOT_FOUND("ATTRIBUTE NOT FOUND", MediaFileTagValueDataType.NONE),
	
	;
	
	private MediaFileTagValueDataType dataType;
	
	private String attributeName;
		
	
	private MediaFileTagAttributeType( String attributeName, MediaFileTagValueDataType dataType){
		this.attributeName = attributeName;
		this.dataType = dataType;
	}
	
	public String getAttributeName(){
		return attributeName;
	}
	
	public MediaFileTagValueDataType getDataType(){
		return dataType;
	}
	
	public boolean isAttributeValueProperlyFormatted(String anAttributeDataValue){
		boolean isValidFormat = false;
		if (this.getDataType().matchesDataType(anAttributeDataValue)){
			isValidFormat = isAllowedValue(anAttributeDataValue);
		}
		return isValidFormat;
	}
	
	/**
	 * Override for custom validations
	 * @param anAttributeDataValue
	 * @return
	 */
	protected boolean isAllowedValue(String anAttributeDataValue){
		return true;
	}
	
	protected boolean isAllowedValueYesNo(String anAttributeDataValue){
		String[] allowedValues = new String []{"YES", "NO"};
		if (Arrays.asList(allowedValues).contains(anAttributeDataValue)){
			return true; 
		}
		return false; 
	}
	
	public static MediaFileTagAttributeType getAttributeTypeFromString(String anAttributeName){
		for(MediaFileTagAttributeType type : MediaFileTagAttributeType.values()){
			if (type.getAttributeName().equals(anAttributeName)){
				return type;
			}
		}
		return NOT_FOUND;
	}
	
//	private boolean isAllowedValue(String anAttributeValue){
//		for(String allowedValue : this.getEnumeratedAttributeSet()){
//			if(allowedValue.equals(anAttributeValue)){
//				return true; 
//			}
//		}
//		return false;
//	}
	
	//TODO - may need to allow for custom rule checks
	
	//TODO - add abstract method to return enumerated values. 

}
