package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagValueDataType {
	
	NONE("\\z"), //Should be an empty string ("\\z") should I always check for a line end?
	
	INTEGER("\\d+"), // ("\\d+")
	
	ATTRIBUTE_LIST("^((\\w|-)+=\\S+,?)+"), //expect /w followed by = and then optionally allow a , followed by zero or more ("^(0\\w+=\\S+,?)+"). Will need to be split on comma
	
	DECIMAL_INTEGER("\\d{1,20}") , //("\\d{1-20}") Does this need to be validated separately from the INTEGER type?
	
	QUOTED_STRING("^\"\\w+\"$"),  
	
	ENUMERATED_STRING("\\w+"), //("\\w+") delegate matching the enumerated types defined by the attribute.  
	
	DECIMAL_RESOLUTION("^\\d+x\\d+"), //("^\\d+x\\d+")
	
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
