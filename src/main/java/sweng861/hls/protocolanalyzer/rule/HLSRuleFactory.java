package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.List;

public class HLSRuleFactory {
	
	public static List<HLSRule> getFileRules(){
		
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new FileMustBeRecognizedRule());
		ruleList.add(new FileHasProperTagsRule());
		ruleList.add(new TagsMustBeInProperSequenceRule());
		ruleList.add(new MediaSequenceFilesAreValidForPlaylist());
		ruleList.add(new FileHasCorrectNumberOfTagsRule());
		ruleList.add(new FileDoesNotContainImproperTagsRule());
		ruleList.add(new TagsAreProperlyFormattedRule());

		return ruleList;
	}
	
	
}
