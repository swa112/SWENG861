package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagAttributeType {
	
	BANDWIDTH,
	
	AVERAGE_BANDWITH,
	
	CODECS,
	
	RESOLUTION,
	
	AUDIO,
	
	VIDEO,
	
	SUBTITLES,
	
	CLOSED_CAPTIONS,
	
	TYPE,
	
	URI,
	
	GROUP_ID,
	
	LANGUAGE,
	
	ASSOC_LANGUAGE,
	
	NAME,
	
	DEFAULT,
	
	AUTO_SELECT,
	
	FORCED,
	
	INSTREAM_ID,
	
	CHARACTERISTICS,
	
	
	;
	
	private MediaFileTagValueDataType dataType;
	
	private MediaFileTagAttributeType(/*MediaFileTagValueDataType dataType*/){
//		this.dataType = dataType;
	}
	
	public MediaFileTagValueDataType getDataType(){
		return dataType;
	}
	
	//TODO - map the attribute to it's data type. 
	//TODO - may need to allow for custom rule checks

}
