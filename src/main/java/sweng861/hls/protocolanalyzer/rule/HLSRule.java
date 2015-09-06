package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public interface HLSRule {
	
	public void runRuleCheck(HLSMediaFile file);
	
	public boolean handlesRequest(HLSMediaFile file);

}
