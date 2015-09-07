package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public interface HLSRule {
	
	public void runRuleCheck(HLSMediaEntity file);
	
	public boolean handlesRequest(HLSMediaEntity file);

}
