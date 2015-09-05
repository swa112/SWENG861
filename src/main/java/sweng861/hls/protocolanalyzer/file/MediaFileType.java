package sweng861.hls.protocolanalyzer.file;

public enum MediaFileType {
	
	MASTER_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getOptionTags() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	SECOND_LEVEL_PLAYLIST {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getOptionTags() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	MEDIA_SEGMENT {
		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getOptionTags() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	NOT_FOUND {

		@Override
		public MediaFileTagType[] getRequiredTags() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MediaFileTagType[] getOptionTags() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	;
	
	public abstract MediaFileTagType[] getRequiredTags();
	
	public abstract MediaFileTagType[] getOptionTags();

}
