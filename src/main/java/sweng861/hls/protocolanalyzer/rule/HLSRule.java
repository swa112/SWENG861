package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;

public interface HLSRule {
	
	public void runRuleCheck(HLSMediaFile file);
	
	public void runRuleCheck(HLSMediaFile file, HLSMediaFileLineInfo lineInfo);
	
//	public boolean handlesRequest(HLSMediaEntity file);

}
