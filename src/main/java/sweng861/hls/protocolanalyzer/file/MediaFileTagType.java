package sweng861.hls.protocolanalyzer.file;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.annotation.DeprecatedFileEntity;
import sweng861.hls.protocolanalyzer.annotation.TagMustBeUnique;


/**
 * Enumerated type that represents a media file tag type. Add new types to extend the set of tag types when the specification is updated. 
 * @author Scott
 *
 */
public enum MediaFileTagType {
	
	//**********Basic Tags**************//
	
	EXTM3U("^#EXTM3U$", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}

	},
	
	@TagMustBeUnique
	EXT_X_VERSION("^#EXT-X-VERSION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.INTEGER;
		}
	},
	
	//**********Media Playlist Tags**************//
	
	EXT_X_TARGET_DURATION("^#EXT-X-TARGETDURATION.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}

	},
	
	EXT_X_MEDIA_SEQUENCE("^#EXT-X-MEDIA-SEQUENCE.+$", false, true){

		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}
	},
	
	EXT_X_ENDLIST("^#EXT-X-ENDLIST$", false, false){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}
	},
	
	EXT_X_DISCONTINUITY_SEQUENCE("^#EXT-X-DISCONTINUITY-SEQUENCE.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}
	},
	
	EXT_X_PLAYLIST_TYPE("^#EXT-X-PLAYLIST-TYPE.$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ENUMERATED_STRING;
		}
	},
	
	EXT_I_FRAMES_ONLY("^#EXT-I-FRAMES-ONLY$", false, false){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}
	},
	
	//*********Master or Media Segment Tags*********//
	
	//**********Master Playlist Tags**************//
	
	EXT_X_STREAM_INF("^#EXT-X-STREAM-INF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}

		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {MediaFileTagAttributeType.BANDWIDTH}; //Is URI required.
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
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
		
		@Override
		public boolean isTagFollowedByRequiredType(MediaFileTagType typeForNextLine){
			if(typeForNextLine.equals(MediaFileTagType.ABSOLUTE_PLAYLIST_URI) || typeForNextLine.equals(MediaFileTagType.RELATIVE_PLAYLIST_URI)){
				return true;
			} else {
				return false; 
			}
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
	
	EXT_X_I_FRAME_STREAM_INF("^#EXT-X-I-FRAME-STREAM-INF.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		
		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.BANDWIDTH,
					MediaFileTagAttributeType.URI};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
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
	
	EXT_X_SESSION_DATA("^#EXT-X-SESSION-DATA.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		//TODO add required and optional attributes
	},
	

	
	EXT_X_INDEPENDENT_SEGMENTS("^#EXT-X-INDEPENDENT-SEGMENTS$", false, false){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}
	},
	
	EXT_X_START("^#EXT-X-START.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		//TODO add required and optional tags. 
	},
	
	//**********Media Segment Tags**************//
	
	EXTINF("^#EXTINF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.EXT_INF_CUSTOM;
		}
		
		@Override
		public boolean isTagFollowedByRequiredType(MediaFileTagType typeForNextLine){
			if(typeForNextLine.equals(TRANSPORT_STREAM_URI)){
				return true;
			} else {
				return false; 
			}
		}
	},
	
	EXT_X_BYTERANGE("^#EXT-X-BYTERANGE.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.DECIMAL_INTEGER;
		}
	}, 
	
	EXT_X_DISCONTINUITY("^#EXT-X-DISCONTINUITY.+$", false, false){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}
	},
	
	EXT_X_KEY("^#EXT-X-KEY.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		//TODO add required and optional attributes
	}, 
	
	EXT_X_MAP("^#EXT-X-MAP.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		//TODO add required and optional attributes
	}, 
	
	EXT_X_PROGRAM_DATE_TIME("^#EXT-X-PROGRAM-DATE-TIME.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY; //TODO add ISO format
		}
	},
	
	//*********Deprecated Tags*******//
	
	@DeprecatedFileEntity(asOf="version 6")
	EXT_X_ALLOW_CACHE("^#EXT-X-ALLOW-CACHE.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return MediaFileTagValueDataType.ANY;
		}
		
		@Override
		public boolean isDeprecated(){
			return true;
		}
	},
	
	//**********URI Tags**************//
	
	ABSOLUTE_PLAYLIST_URI("^http:\\\\.+\\.(m3u8|m3u)$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	
	RELATIVE_PLAYLIST_URI("^.+\\.(m3u8|m3u)$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY;
		}

	},
	
	TRANSPORT_STREAM_URI("^.+\\.ts$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY;
		}
	},
	
	//**********Comment and Not found Tags**************//

	COMMENT("^#.+$", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	
	NOT_A_TAG("", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
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
	
	/**
	 * Gets the data type associated with the tag. Must be overriden. 
	 * @return
	 */
	public abstract MediaFileTagValueDataType getValueDataType();
	
	
	/**
	 * Gets the required attributes for the tab, by default returns an empty array. 
	 * Override this for tags that have required attributes. 
	 * @return
	 */
	public MediaFileTagAttributeType[] getRequiredAttributes() {
		return new MediaFileTagAttributeType [0];
	}

	/**
	 * Gets the optional attributes for the tab, by default returns an empty array. 
	 * Override this for tags that have optional attributes. 
	 * @return
	 */
	public MediaFileTagAttributeType[] getOptionalAttributes() {
		return new MediaFileTagAttributeType [0];
	}
	
	/**
	 * Override to set a required following type. By default returns true. 
	 * @return
	 */
	public boolean isTagFollowedByRequiredType(MediaFileTagType typeForNextLine){
		return true;
	}
	/**
	 * Checks that tag is properly formatted with the associated data type. 
	 * @param aTagDataValue
	 * @return
	 */
	public boolean isTagProperlyFormatted(String aTagDataValue){
		boolean isProperlyFormatted = false;
		if (aTagDataValue.matches(this.getValueDataType().getDataTypeRegEx())){
			isProperlyFormatted = true;
		}
		return isProperlyFormatted;
	}
	
	public boolean isDeprecated(){
		return false; 
	}
	
	public static List<MediaFileTagType> getTagsThatMustBeUniquePerFile(){
		List<MediaFileTagType> uniqueTags = new ArrayList<MediaFileTagType>();
		Field[] fields = MediaFileTagType.class.getFields();
		for (Field field : fields){
			TagMustBeUnique annotation = field.getAnnotation(TagMustBeUnique.class);
			if(annotation != null ){
				uniqueTags.add(MediaFileTagType.valueOf(field.getName()));
			}
		}
		return uniqueTags; 

	}

	//TODO - add abstract method to indicate that the tag needs to check a URI on the next line.  (Dependent tags)
	//TODO - add abstract method to indicate the tag has a custom rule. 
	
	public static void main(String [] args){
		String test = "#EXT-X-TARGET-DURATION:10";
		System.out.println(test.matches(EXT_X_TARGET_DURATION.getTagPattern()));
		
	}

}
