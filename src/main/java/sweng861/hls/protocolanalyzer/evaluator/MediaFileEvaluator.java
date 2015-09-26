package sweng861.hls.protocolanalyzer.evaluator;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.rule.HLSRule;
import sweng861.hls.protocolanalyzer.rule.HLSRuleFactory;

public class MediaFileEvaluator implements Evaluator {

	public void evaluate(List<HLSMediaFile> files) {
		List<HLSRule> fileLevelRules = HLSRuleFactory.getFileRules();
//		List<HLSRule> tagLevelRules = HLSRuleFactory.getTagRules();
		for(HLSMediaFile file : files){
			for(HLSRule rule : fileLevelRules){
				rule.runRuleCheck(file);
			}
//			validateFileTags(tagLevelRules, file);
			
		}
		
	}
	
//	private void validateFileTags(List<HLSRule> tagRules, HLSMediaFile file){
//		List<HLSMediaFileLineInfo> fileLines = file.getFileLines();
//		for (HLSMediaFileLineInfo line : fileLines){
//			
//			if(line.getLineType().isEvaluateTag()){
//				for(HLSRule rule : tagRules){
//					rule.runRuleCheck(file, line);
//				}
//			}
//		}
//	}

} 
