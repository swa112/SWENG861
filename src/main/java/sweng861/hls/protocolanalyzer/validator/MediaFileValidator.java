package sweng861.hls.protocolanalyzer.validator;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.rule.HLSRule;
import sweng861.hls.protocolanalyzer.rule.HLSRuleFactory;

public class MediaFileValidator implements Validator {

	public void validate(List<HLSMediaFile> files) {
		List<HLSRule> rules = HLSRuleFactory.getRules();
		for(HLSMediaFile file : files){
			for(HLSRule rule : rules){
				rule.runRuleCheck(file);
			}
		}
		//Loop over files, call file level rules on file.
		//Then loop over lines, calling line level rules for each line. 
		
//		MediaFileType fileType = file.getFileType();
//		String firstLine = file.getFileLines().get(0);
//		if(! firstLine.matches(fileType.getStartTag().getTagPattern())){
//			
//		}
		
		//Determine tags in file, and evaluate required and allowed for each file. 
		
		//Determine if tags that can only have one per file are unique. 
		
	}

}
