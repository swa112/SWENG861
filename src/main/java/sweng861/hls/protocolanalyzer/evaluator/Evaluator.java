package sweng861.hls.protocolanalyzer.evaluator;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public interface Evaluator {
	
	public void validate(List<HLSMediaFile> files);

}
