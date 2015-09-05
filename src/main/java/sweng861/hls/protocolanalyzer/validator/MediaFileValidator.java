package sweng861.hls.protocolanalyzer.validator;

import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public interface MediaFileValidator {
	
	public List<ValidationErrorLogEntry> validate(HLSMediaFile file);

}
