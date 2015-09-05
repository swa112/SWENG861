package sweng861.hls.protocolanalyzer.validator;

import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

@XmlRootElement
public class ValidationErrorLogEntry{
	
	private ValidationErrorSeverityType errorType; 
	
	private String fileName;
	
	private String message; 
	
	public ValidationErrorLogEntry(ValidationErrorSeverityType errorType, HLSMediaFile file, String message){
		this.errorType = errorType; 
		this.fileName = file.getFileName();
		this.message = message; 
	}
	
	public ValidationErrorLogEntry(){
		
	}

	public ValidationErrorSeverityType getErrorType() {
		return errorType;
	}

	public void setErrorType(ValidationErrorSeverityType errorType) {
		this.errorType = errorType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
