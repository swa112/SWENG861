package sweng861.hls.protocolanalyzer.validator;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileType;

public class MediaFileValidator implements Validator {

	public void validate(HLSMediaFile file) {
		MediaFileType fileType = file.getFileType();
		String firstLine = file.getFileLines().get(0);
		if(! firstLine.matches(fileType.getStartTag().getTagPattern())){
			
		}
		
		//Determine tags in file, and evaluate required and allowed for each file. 
		
		//Determine if tags that can only have one per file are unique. 
		
	}

}
