package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.List;

public class HLSRuleFactory {
	
	public static List<HLSRule> getRules(){
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new FileMustBeRecognizedRule());
		return ruleList;
	}

}
