package sweng861.hls.protocolanalyzer.file;

public enum MediaFileTagType {
	
	//**********Basic Tags**************//
	
	EXTM3U("^#EXTM3U$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	EXT_X_VERSION("^#EXT-X-VERSION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	//**********Master Playlist Tags**************//
	
	EXT_X_STREAM_INF("^#EXT-X-STREAM-INF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	//**********Media Playlist Tags**************//
	
	EXT_X_TARGET_DURATION("^#EXT-X-TARGETDURATION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	//**********Media Segment Tags**************//
	
	EXTINF("^#EXTINF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	//**********URI Tags**************//
	
	ABSOLUTE_PLAYLIST_URI("^http:\\\\.+\\.m3u8$|^http:\\\\.+\\.m3u$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	RELATIVE_PLAYLIST_URI("^.+\\.m3u8$|^.+\\.m3u$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	TRANSPORT_STREAM_URI("^.+\\.ts$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	//**********Comment and Not found Tags**************//

	COMMENT("^#.+$", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	NOT_A_TAG("", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagAttributeType[] getAllowedAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	;
	
	private String tagPattern; 
	
	private boolean isURI; 
	
	private boolean isEvaluateTag; 
	
	private MediaFileTagType(String tagPattern, boolean isURI, boolean isEvaluateTag){
		this.tagPattern = tagPattern;
		this.isURI = isURI;
		this.isEvaluateTag = isEvaluateTag;
	}
	
	public String getTagPattern(){
		return tagPattern;
	}
	
	public boolean isURI(){
		return isURI;
	}
	
	public boolean isEvaluateTag(){
		return isEvaluateTag;
	}
	
	public static MediaFileTagType findTagByLine(String aFileLine){
		for(MediaFileTagType tag : MediaFileTagType.values()){
			if (aFileLine.matches(tag.getTagPattern())){
				return tag;
			}
		}
		return NOT_A_TAG;
	}
	
	public abstract MediaFileTagValueDataType getValueDataType();
	
	public abstract MediaFileTagAttributeType[] getRequiredAttributes();
	
	public abstract MediaFileTagAttributeType[] getAllowedAttributes();

	//TODO - add abstract method to indicate that the tag needs to check a URI on the next line.  (Dependent tags)
	//TODO - add abstract method to indicate the tag has a custom rule. 
	
	public static void main(String [] args){
		String test = "#EXT-X-TARGET-DURATION:10";
		System.out.println(test.matches(EXT_X_TARGET_DURATION.getTagPattern()));
		
	}

}
