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
	
	ATTRIBUTE_LIST("^((\\w|-)+=\\S+,?)+"), 
	
	DECIMAL_INTEGER("\\d{1,20}") , 
	
	QUOTED_STRING("^\"\\w+\"$"),  
	
	ENUMERATED_STRING("\\w+"),
	
	DECIMAL_RESOLUTION("^\\d+x\\d+"),
	
	DECIMAL_FLOATING_POINT("^\\d+\\.\\d+$"),
	
	EXT_INF_CUSTOM("^\\d+(\\.\\d+)?,(.+)?$"),
	
	SIGNED_DECIMAL_FLOATING_POINT("^([0-9]-\\.)+$"),
	
	HEXADECIMAL_SEQUENCE("^(0x|0X)[0-9][A-F]+$"),
	
	//TODO- when needed add more data types
	
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
