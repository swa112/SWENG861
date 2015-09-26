package sweng861.hls.protocolanalyzer.file;

/**
 * Represents the data types for tags and attributes. 
 * @author Scott
 *
 */
public enum MediaFileTagValueDataType {
	
	NONE("\\z"), //Should be an empty string ("\\z") should I always check for a line end?
	
	ANY("^.+$"),
	
	INTEGER("\\d+"),
	
	ATTRIBUTE_LIST("^((\\w|-)+=\\S+,?\\s?)+"), 
	
	DECIMAL_INTEGER("\\d{1,20}") , 
	
	QUOTED_STRING("^\"\\w+\"$"),  
	
	ENUMERATED_STRING("^\\w+$"),
	
	DECIMAL_RESOLUTION("^\\d+x\\d+"),
	
	DECIMAL_FLOATING_POINT("^\\d+\\.\\d+"),
	
	EXTINF_CUSTOM("^\\d+(\\.\\d+)?,(.+)?$"),
	
	EXTINF_INTEGER(DECIMAL_INTEGER.getDataTypeRegEx() + "(.+)?$"),
	
	EXTINF_FLOATING_POINT(DECIMAL_FLOATING_POINT.getDataTypeRegEx() + "(.+)?$"),
	
	SIGNED_DECIMAL_FLOATING_POINT("^([0-9]-\\.)+$"),
	
	HEXADECIMAL_SEQUENCE("^(0x|0X)[0-9][A-F]+$"),
	
	ISO_DATE("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{3}Z")
	
	;
	
	private String dataTypeRegEx;
	
	private MediaFileTagValueDataType(String dataTypeRegEx){
		this.dataTypeRegEx = dataTypeRegEx;
	}
	
	public String getDataTypeRegEx(){
		return dataTypeRegEx;
	}
	
	public boolean matchesDataType(String aDataValue){
		return  aDataValue.matches(this.getDataTypeRegEx());
	}	
	
	
	
	//To Do - add a validation method that can be used to verify the formats. 

}
