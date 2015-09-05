package sweng861.hls.protocolanalyzer.validator;

import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

@XmlRootElement
public class ValidationErrorLogEntry{
	
	private ValidationErrorSeverityType errorType; 
	
	private String message; 
	
	public ValidationErrorLogEntry(ValidationErrorSeverityType errorType,  String message){
		this.errorType = errorType; 
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

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
