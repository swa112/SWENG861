package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.sequencing.TableSequence;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public class HLSRuleFactory {
	
	public static List<HLSRule> getFileRules(HLSMediaFile masterPlaylist){
		
		List<HLSRule> ruleList = new ArrayList<HLSRule>();
		ruleList.add(new FileMustBeRecognizedRule());
		ruleList.add(new FileHasProperTagsRule());
		ruleList.add(new TagsMustBeInProperSequenceRule());
		ruleList.add(new MediaSequenceFilesAreValidForPlaylist());
		ruleList.add(new FileHasCorrectNumberOfTagsRule());
		ruleList.add(new FileDoesNotContainImproperTagsRule());
		ruleList.add(new TagsAreProperlyFormattedRule());
		ruleList.add(new TSDurationMatchesBandwidth(masterPlaylist));
		return ruleList;
	}
	
	
}
