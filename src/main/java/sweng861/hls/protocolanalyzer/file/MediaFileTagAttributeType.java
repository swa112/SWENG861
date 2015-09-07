package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagAttributeType {
	
	BANDWIDTH ("BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	AVERAGE_BANDWITH ("AVERAGE-BANDWIDTH", MediaFileTagValueDataType.DECIMAL_INTEGER),
	
	CODECS("CODECS", MediaFileTagValueDataType.QUOTED_STRING),
	
	RESOLUTION("RESOLUTION", MediaFileTagValueDataType.DECIMAL_RESOLUTION),
	
	AUDIO("AUDIO", MediaFileTagValueDataType.QUOTED_STRING),
	
	VIDEO("VIDEO", MediaFileTagValueDataType.QUOTED_STRING),
	
	SUBTITLES("SUBTITLES", MediaFileTagValueDataType.QUOTED_STRING),
	
	CLOSED_CAPTIONS("CLOSED-CAPTINS", MediaFileTagValueDataType.QUOTED_STRING),
	
	TYPE("TYPE", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	URI("URI", MediaFileTagValueDataType.QUOTED_STRING),
	
	GROUP_ID("GROUP-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	LANGUAGE("LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	ASSOC_LANGUAGE("ASSOC-LANGUAGE", MediaFileTagValueDataType.QUOTED_STRING),
	
	NAME("NAME", MediaFileTagValueDataType.QUOTED_STRING),
	
	DEFAULT("DEFAULT", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	AUTO_SELECT("AUTO-SELECT", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	FORCED("FORCED", MediaFileTagValueDataType.ENUMERATED_STRING),
	
	INSTREAM_ID("INSTREAM-ID", MediaFileTagValueDataType.QUOTED_STRING),
	
	CHARACTERISTICS("CHARACTERISTICS", MediaFileTagValueDataType.QUOTED_STRING),
	
	
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
