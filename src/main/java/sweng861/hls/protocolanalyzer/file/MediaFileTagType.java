package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagType {
	
	EXTM3U("^#EXTM3U.+$"),
	
	EXT_X_VERSION("^#EXT-X-VERSION.+$"),
	
	EXT_X_STREAM_INF("^#EXT-X-STREAM-INF.+$"),
	
	EXT_X_TARGET_DURATION("^#EXT-X-TARGETDURATION.+$"),
	
	EXTINF("^#EXTINF.+$"),
	
	NOT_A_TAG(""),
	
	;
	
	private String tagPattern; 
	
	private MediaFileTagType(String tagPattern){
		this.tagPattern = tagPattern;
	}
	
	public String getTagPattern(){
		return tagPattern;
	}
	
	public static MediaFileTagType findTagByLine(String aFileLine){
		for(MediaFileTagType tag : MediaFileTagType.values()){
			if (aFileLine.matches(tag.getTagPattern())){
				return tag;
			}
		}
		return NOT_A_TAG;
	}
	
	public static void main(String [] args){
		String test = "#EXT-X-TARGET-DURATION:10";
		System.out.println(test.matches(EXT_X_TARGET_DURATION.getTagPattern()));
		
	}

}
