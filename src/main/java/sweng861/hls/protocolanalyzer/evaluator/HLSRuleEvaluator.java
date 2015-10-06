package sweng861.hls.protocolanalyzer.evaluator;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.rule.HLSRule;
import sweng861.hls.protocolanalyzer.rule.HLSRuleFactory;

public class HLSRuleEvaluator implements Evaluator {

	public void evaluate(List<HLSMediaFile> files) {
		List<HLSRule> fileLevelRules = HLSRuleFactory.getFileRules(null);

		for(HLSMediaFile file : files){
			for(HLSRule rule : fileLevelRules){
				rule.runRuleCheck(file);
			}
			
		}
	}
	
} 
