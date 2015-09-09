package sweng861.hls.protocolanalyzer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;


@XmlRootElement
public class MediaStreamAnalyzerResult {
	//Contains high level errors
	private List<ValidationErrorLogEntry> errors = new ArrayList<ValidationErrorLogEntry>();
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
	
//	public List<ValidationErrorLogEntry> getAllErrors(){
//		List<ValidationErrorLogEntry> aggregateList = new ArrayList<ValidationErrorLogEntry>();
//		aggregateList.addAll(errors);
//		for (HLSMediaFile file : files){
//			aggregateList.addAll(file.getValidationErrors());
//		}
//		return aggregateList;
//	}

}
