package sweng861.hls.protocolanalyzer;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;


@XmlRootElement
public class MediaStreamAnalyzerResult {
	//Contains high level errors
	private List<ValidationErrorLogEntry> errors;
	//Contains file information
	private List<HLSMediaFile> files;

	
	public List<ValidationErrorLogEntry> getErrors() {
		return errors;
	}
	public void setErrors(List<ValidationErrorLogEntry> errors) {
		this.errors = errors;
	}
	public List<HLSMediaFile> getFiles() {
		return files;
	}
	public void setFiles(List<HLSMediaFile> files) {
		this.files = files;
	}

}
