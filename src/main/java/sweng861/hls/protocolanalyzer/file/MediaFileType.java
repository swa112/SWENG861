package sweng861.hls.protocolanalyzer.file;

import java.util.List;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum MediaFileType {
	
	MASTER_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getAllowedTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			// TODO Auto-generated method stub
			return MediaFileTagType.EXT_X_STREAM_INF;
		}

		@Override
		public MediaFileTagType getStartTag() {
			// TODO Auto-generated method stub
			return MediaFileTagType.EXTM3U;
		}
	},
	
	MEDIA_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getAllowedTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			// TODO Auto-generated method stub
			return MediaFileTagType.EXT_X_TARGET_DURATION;
		}

		@Override
		public MediaFileTagType getStartTag() {
			// TODO Auto-generated method stub
			return MediaFileTagType.EXTM3U;
		}
	},
	
//	MEDIA_SEGMENT {
//		@Override
//		public MediaFileTagType[] getRequiredTags() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public MediaFileTagType[] getAllowedTags() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public MediaFileTagType getFileIdentifyingTag() {
//			// TODO Auto-generated method stub
//			return MediaFileTagType.EXTINF;
//		}
//	},
	
	INVALID_FILE {

		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getAllowedTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType getFileIdentifyingTag() {
			// TODO Auto-generated method stub
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
	
	public abstract MediaFileTagType[] getRequiredTags();
	
	public abstract MediaFileTagType[] getAllowedTags();
	
	public abstract MediaFileTagType getFileIdentifyingTag();
	
	public abstract MediaFileTagType getStartTag();
	
	public static MediaFileType matchFileTypOnIdentifyingTag(List<String> fileLines){
		for(MediaFileType file : MediaFileType.values()) {
			MediaFileTagType fileIdentifyingTag = file.getFileIdentifyingTag();
			for(String line : fileLines){
				if (line.matches(fileIdentifyingTag.getTagPattern())){
					return file;
				}
			}
		}
		return INVALID_FILE;
	}

}
