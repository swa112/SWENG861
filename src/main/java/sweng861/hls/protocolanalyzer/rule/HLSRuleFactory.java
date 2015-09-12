package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.List;

public class HLSRuleFactory {
	
	public static List<HLSRule> getFileRules(){
			
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new FileMustBeRecognizedRule());
		ruleList.add(new FileHasProperTagsRule());
		ruleList.add(new TagsMustBeInProperSequenceRule());
		ruleList.add(new MediaSequenceFilesMustBeInOrder());
		ruleList.add(new FileHasCorrectNumberOfTagsRule());
		return ruleList;
	}
	
	public static List<HLSRule> getTagRules(){
			
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new TagsAreProperlyFormattedRule());
		return ruleList;
	}
	
	//TODO - use reflection to instantiate all instances that implement HLSRule. Get a list of classes within the package and test that they 
	//are assignable from the abstract class to create the rule sets. 
	


}
