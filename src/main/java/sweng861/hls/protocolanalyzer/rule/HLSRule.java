package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.file.HLSMediaFileEntity;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;

public interface HLSRule {
	
	public void runRuleCheck(HLSMediaFile file);
	


}
