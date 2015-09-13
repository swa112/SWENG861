package sweng861.hls.protocolanalyzer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;


@XmlRootElement
public class MediaStreamAnalyzerResult {
	//Contains high level errors
	private List<ErrorLogEntry> errors = new ArrayList<ErrorLogEntry>();
	//Contains file information
	private List<HLSMediaFile> files;

	
	public List<ErrorLogEntry> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorLogEntry> errors) {
		this.errors = errors;
	}
	
	public void addError (ErrorLogEntry error){
		this.errors.add(error);
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
