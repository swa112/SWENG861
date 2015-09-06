package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.List;

public class HLSRuleFactory {
	
	public static List<HLSRule> getRules(){
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new FileMustBeRecognizedRule());
		return ruleList;
	}
	
	//TODO - use reflection to instantiate all instances that implement HLSRule. 
	
	//TODO - add method to getRules for Line Level vs. File Level checks unless there is a way to keep this abstract. 

}
