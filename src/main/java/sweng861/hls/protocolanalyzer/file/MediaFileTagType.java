package sweng861.hls.protocolanalyzer.file;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sweng861.hls.protocolanalyzer.annotation.DeprecatedProtocol;
import sweng861.hls.protocolanalyzer.annotation.FollowedBy;
import sweng861.hls.protocolanalyzer.annotation.Times;
import sweng861.hls.protocolanalyzer.annotation.TimesType;


/**
 * Enumerated type that represents a media file tag type. Add new types to extend the set of tag types when the specification is updated. 
 * @author Scott
 *
 */
public enum MediaFileTagType {
	
	//**********URI Tags**************//
	
	ABSOLUTE_PLAYLIST_URI("^https?:\\\\.+\\.(m3u8|m3u)$", true, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY;
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
	
	//**********Basic Tags**************//
	
	EXTM3U("^#EXTM3U$", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}

	},
	
	@Times(TimesType.ONCE)
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
	
	EXT_X_I_FRAMES_ONLY("^#EXT-I-FRAMES-ONLY$", false, false){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.NONE;
		}
	},
	
	
	//**********Master Playlist Tags**************//
	
	@Times(TimesType.MORE_THAN_ONCE)
	@FollowedBy({MediaFileTagType.ABSOLUTE_PLAYLIST_URI, MediaFileTagType.RELATIVE_PLAYLIST_URI })
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
		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.DATA_ID,
			};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.URI,
					MediaFileTagAttributeType.LANGUAGE,
				
			};
		}
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
		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.TIME_OFFSET,
			};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.PRECISE,				
			};
		}
	},
	
	//**********Media Segment Tags**************//
	
	@FollowedBy({MediaFileTagType.TRANSPORT_STREAM_URI})
	EXTINF("^#EXTINF.+$", false, true) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.EXTINF_CUSTOM;
		}
		
//		@Override
//		public boolean isTagProperlyFormatted(String aTagDataValue, int aVersionNumber){
//			if(aVersionNumber < 3 ){
//				return aTagDataValue.matches(MediaFileTagValueDataType.EXTINF_INTEGER.getDataTypeRegEx());
//			} 
//			
//			return aTagDataValue.matches(this.getValueDataType().getDataTypeRegEx());			
//		}
		
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
		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.METHOD,
					MediaFileTagAttributeType.URI,
			};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.IV,
					MediaFileTagAttributeType.KEY_FORMAT,
					MediaFileTagAttributeType.KEY_FORMAT_VERSIONS,
					
			};
		}
	}, 
	
	EXT_X_MAP("^#EXT-X-MAP.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ATTRIBUTE_LIST;
		}
		@Override
		public MediaFileTagAttributeType[] getRequiredAttributes() {
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.URI,
			};
		}

		@Override
		public MediaFileTagAttributeType[] getOptionalAttributes() {
	
			return new MediaFileTagAttributeType[] {
					MediaFileTagAttributeType.BYTE_RANGE,					
			};
		}
	}, 
	
	EXT_X_PROGRAM_DATE_TIME("^#EXT-X-PROGRAM-DATE-TIME.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ISO_DATE;
		}
	},
	
	//*********Deprecated Tags*******//
	
	@DeprecatedProtocol(asOf="version 6")
	EXT_X_ALLOW_CACHE("^#EXT-X-ALLOW-CACHE.+$", false, true){
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY;
		}
		
		@Override
		public boolean isDeprecated(){
			return true;
		}
	},
	

	
	//**********Comment and Not found Tags**************//

	COMMENT("^#[^EXT].+$", false, false) {
		@Override
		public MediaFileTagValueDataType getValueDataType() {
			return MediaFileTagValueDataType.ANY;
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
	 * Checks that tag is properly formatted with the associated data type. 
	 * @param aTagDataValue
	 * @return
	 */
	public boolean isTagProperlyFormatted(String aTagDataValue, int aVersionNumber){
		boolean isProperlyFormatted = false;
		if (aTagDataValue.matches(this.getValueDataType().getDataTypeRegEx())){
			isProperlyFormatted = true;
		}
		return isProperlyFormatted;
	}
	
	public boolean isDeprecated(){
		return false; 
	}
	
	public static List<MediaFileTagType> getTagsWithTimesConstraint(){
		List<MediaFileTagType> tagsWithTimesConstraint = new ArrayList<MediaFileTagType>();
		Field[] fields = MediaFileTagType.class.getFields();
		for (Field field : fields){
			Times annotation = field.getAnnotation(Times.class);
			if(annotation != null ){
				tagsWithTimesConstraint.add(MediaFileTagType.valueOf(field.getName()));
			}
		}
		return tagsWithTimesConstraint; 

	}
	
	@Override
	public String toString(){
		String name = this.name();
		return name.replace('_', '-');
	}

	
	public static void main(String [] args){
		String test = "#EXT-X-TARGET-DURATION:10";
		System.out.println(test.matches(EXT_X_TARGET_DURATION.getTagPattern()));
		
	}

}
