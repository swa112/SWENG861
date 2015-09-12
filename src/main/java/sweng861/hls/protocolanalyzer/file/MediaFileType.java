package sweng861.hls.protocolanalyzer.file;

import java.util.List;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum MediaFileType {
	
	MASTER_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			return new MediaFileTagType [] {
					MediaFileTagType.EXTM3U, 
					MediaFileTagType.EXT_X_STREAM_INF, 
					MediaFileTagType.RELATIVE_PLAYLIST_URI};
		}

		@Override
		public MediaFileTagType[] getOptionalTags() {
			// TODO Auto-generated method stub
			return new MediaFileTagType [] {};
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			return MediaFileTagType.EXT_X_STREAM_INF;
		}

		@Override
		public MediaFileTagType getStartTag() {
			return MediaFileTagType.EXTM3U;
		}
	},
	
	MEDIA_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			return new MediaFileTagType [] {
					MediaFileTagType.EXTM3U, 
					MediaFileTagType.EXT_X_TARGET_DURATION,
					MediaFileTagType.TRANSPORT_STREAM_URI, 
					MediaFileTagType.EXTINF};
		}

		@Override
		public MediaFileTagType[] getOptionalTags() {
			return new MediaFileTagType [] {
					MediaFileTagType.EXT_X_MEDIA_SEQUENCE,
					MediaFileTagType.EXT_X_ENDLIST
			}; //TODO
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			return MediaFileTagType.EXT_X_TARGET_DURATION;
		}

		@Override
		public MediaFileTagType getStartTag() {
			return MediaFileTagType.EXTM3U;
		}
	},
	
	
	INVALID_FILE {

		@Override
		public MediaFileTagType[] getRequiredTags() {
			return new MediaFileTagType[0];
		}

		@Override
		public MediaFileTagType[] getOptionalTags() {
			return new MediaFileTagType[0];
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			return MediaFileTagType.NOT_A_TAG;
		}

		@Override
		public MediaFileTagType getStartTag() {
			// TODO Auto-generated method stub
			return MediaFileTagType.NOT_A_TAG;
		}
		
	}
	;
	
	private MediaFileType(){
		
	}
	
	/**
	 * Override with an array of required tags for each file type. 
	 * @return
	 */
	public abstract MediaFileTagType[] getRequiredTags();
	
	/**
	 * Override with an array of optional tags for each file type
	 * @return
	 */
	public abstract MediaFileTagType[] getOptionalTags();
	
	/***
	 * Override with the tag that uniquely identifies the file.
	 * @return
	 */
	public abstract MediaFileTagType getFileIdentifyingTag();
	
	/**
	 * Override with the tag that should be the first line in the file. 
	 * @return
	 */
	public abstract MediaFileTagType getStartTag();
	
	/**
	 * Evaluate the lines that make up the file to determine the corresponding file type. 
	 * @param fileLines
	 * @return
	 */
	public static MediaFileType matchFileTypeOnIdentifyingTag(List<HLSMediaFileLineInfo> fileLines){
		for(MediaFileType file : MediaFileType.values()) {
			MediaFileTagType fileIdentifyingTag = file.getFileIdentifyingTag();
			for(HLSMediaFileLineInfo line : fileLines){
				if (line.getLineType().equals(fileIdentifyingTag)){
					return file;
				}
			}
		}
		return INVALID_FILE;
	}

}
