package sweng861.hls.protocolanalyzer.file;


public enum MediaFileTagType {
	
	//**********Basic Tags**************//
	
	EXTM3U("^#EXTM3U$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
		}
	},
	
	EXT_X_VERSION("^#EXT-X-VERSION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.INTEGER;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
		}
	},
	
	//**********Master Playlist Tags**************//
	
	EXT_X_STREAM_INF("^#EXT-X-STREAM-INF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {MediaFileTagAttributeType.BANDWIDTH};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			// TODO CODECS is a SHOULD
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.AVERAGE_BANDWITH,
					MediaFileTagAttributeType.CODECS,
					MediaFileTagAttributeType.RESOLUTION,
					MediaFileTagAttributeType.AUDIO,
					MediaFileTagAttributeType.VIDEO,
					MediaFileTagAttributeType.SUBTITLES,
					MediaFileTagAttributeType.CLOSED_CAPTIONS
			};
			
		}
	},
	
	EXT_X_MEDIA("^#EXT-X-MEDIA.+$", false, true){

		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.TYPE,
					MediaFileTagAttributeType.GROUP_ID,
					MediaFileTagAttributeType.NAME,
					MediaFileTagAttributeType.INSTREAM_ID
					};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.URI,
					MediaFileTagAttributeType.LANGUAGE,
					MediaFileTagAttributeType.ASSOC_LANGUAGE,
					MediaFileTagAttributeType.DEFAULT,
					MediaFileTagAttributeType.AUTO_SELECT,
					MediaFileTagAttributeType.FORCED,
					MediaFileTagAttributeType.CHARACTERISTICS
			};
		}
		
	},
	
	//**********Media Playlist Tags**************//
	
	EXT_X_TARGET_DURATION("^#EXT-X-TARGETDURATION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
		}
	},
	
	EXT_X_MEDIA_SEQUENCE("^#EXT-X-MEDIA-SEQUENCE.+$", false, true){

		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
		}
		
	},
	
	//**********Media Segment Tags**************//
	
	EXTINF("^#EXTINF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.INTEGER;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
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
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
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
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
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
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
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
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
		}
	},
	
	NOT_A_TAG("", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType [0];
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
			return new MediaFileTagAttributeType [0];
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
	
	public abstract MediaFileTagAttributeType[] getOptionalAttributes();

	//TODO - add abstract method to indicate that the tag needs to check a URI on the next line.  (Dependent tags)
	//TODO - add abstract method to indicate the tag has a custom rule. 
	
	public static void main(String [] args){
		String test = "#EXT-X-TARGET-DURATION:10";
		System.out.println(test.matches(EXT_X_TARGET_DURATION.getTagPattern()));
		
	}

}
