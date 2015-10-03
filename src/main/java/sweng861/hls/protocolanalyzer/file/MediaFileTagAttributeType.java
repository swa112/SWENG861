package sweng861.hls.protocolanalyzer.file;

import java.util.Arrays;

import sweng861.hls.protocolanalyzer.annotation.AllowedValues;
import sweng861.hls.protocolanalyzer.annotation.ProtocolCompatibility;

public enum MediaFileTagAttributeType implements HLSMediaFileEntity {
	
	BANDWIDTH ("BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	AVERAGE_BANDWITH ("AVERAGE-BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	CODECS("CODECS", MediaFileTagValueDataType.QUOTED_STRING),
	
	RESOLUTION("RESOLUTION", MediaFileTagValueDataType.DECIMAL_RESOLUTION),
	
	AUDIO("AUDIO", MediaFileTagValueDataType.QUOTED_STRING),
	
	VIDEO("VIDEO", MediaFileTagValueDataType.QUOTED_STRING),
	
	SUBTITLES("SUBTITLES", MediaFileTagValueDataType.QUOTED_STRING),
	
	CLOSED_CAPTIONS("CLOSED-CAPTINS", MediaFileTagValueDataType.QUOTED_STRING),
	
	@AllowedValues({"AUDIO", "VIDEO", "SUBTITLES", "CLOSED-CAPTIONS"})
	TYPE("TYPE", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	URI("URI", MediaFileTagValueDataType.QUOTED_STRING),
	
	GROUP_ID("GROUP-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	LANGUAGE("LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	ASSOC_LANGUAGE("ASSOC-LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	NAME("NAME", MediaFileTagValueDataType.QUOTED_STRING),
	
	@AllowedValues({"YES", "NO"})
	DEFAULT("DEFAULT", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	@AllowedValues({"YES", "NO"})
	AUTO_SELECT("AUTO-SELECT", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	@AllowedValues({"YES", "NO"})
	FORCED("FORCED", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	INSTREAM_ID("INSTREAM-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	CHARACTERISTICS("CHARACTERISTICS", MediaFileTagValueDataType.QUOTED_STRING),
	
	TIME_OFFSET("TIME-OFFSET", MediaFileTagValueDataType.SIGNED_DECIMAL_FLOATING_POINT), 
	
	@AllowedValues({"YES", "NO"})
	PRECISE("PRECISE", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	DATA_ID ("DATA-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	VALUE("VALUE", MediaFileTagValueDataType.QUOTED_STRING),
	
	@AllowedValues({"NONE", "AES-128", "SAMPLE-AES"})
	METHOD("METHOD", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	@ProtocolCompatibility(version=2)
	IV("IV", MediaFileTagValueDataType.HEXADECIMAL_SEQUENCE),
	
	@ProtocolCompatibility(version=5)
	KEY_FORMAT("KEYFORMAT", MediaFileTagValueDataType.QUOTED_STRING),
	
	@ProtocolCompatibility(version=5)
	KEY_FORMAT_VERSIONS("KEYFORMATVERSIONS", MediaFileTagValueDataType.QUOTED_STRING),  
	
	BYTE_RANGE("BYTERANGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	@ProtocolCompatibility(deprecatedAsOf=6)
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
		AllowedValues values = null; 

		try {
			values = this.getClass().getField(this.name()).getAnnotation(AllowedValues.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(values != null){
			return Arrays.asList(values.value()).contains(anAttributeDataValue);
		}
		return true;
	}
	
	public static MediaFileTagAttributeType getAttributeTypeFromString(String anAttributeName){
		for(MediaFileTagAttributeType type : MediaFileTagAttributeType.values()){
			if (type.getAttributeName().equals(anAttributeName)){
				return type;
			}
		}
		return NOT_FOUND;
	}
	
	@Override
	public String toString(){
		String name = this.name();
		return name.replace('_', '-');
	}

}
