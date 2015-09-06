package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagType {
	
	EXTM3U("^#EXTM3U$", false),
	
	EXT_X_VERSION("^#EXT-X-VERSION.+$", false),
	
	EXT_X_STREAM_INF("^#EXT-X-STREAM-INF.+$", false),
	
	EXT_X_TARGET_DURATION("^#EXT-X-TARGETDURATION.+$", false),
	
	EXTINF("^#EXTINF.+$", false),
	
	ABSOLUTE_PLAYLIST_URI("^http:\\\\.+\\.m3u8$|^http:\\\\.+\\.m3u$", true),
	
	RELATIVE_PLAYLIST_URI("^.+\\.m3u8$|^.+\\.m3u$", true),
	
	TRANSPORT_STREAM_URI("^.+\\.ts$", true),
	
	NOT_A_TAG("", false),
	
	;
	
	private String tagPattern; 
	
	private boolean isURI; 
	
	private MediaFileTagType(String tagPattern, boolean isURI){
		this.tagPattern = tagPattern;
		this.isURI = isURI;
	}
	
	public String getTagPattern(){
		return tagPattern;
	}
	
	public boolean isURI(){
		return isURI;
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
