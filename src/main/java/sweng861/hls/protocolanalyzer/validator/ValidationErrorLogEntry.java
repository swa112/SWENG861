package sweng861.hls.protocolanalyzer.validator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValidationErrorLogEntry{
	
	private ValidationErrorSeverityType errorType; 
	
	private String message; 
	
	private int lineNumber; 
	
	
	public ValidationErrorLogEntry(ValidationErrorSeverityType errorType,  String message, int lineNumber){
		this.errorType = errorType; 
		this.message = message; 
		this.lineNumber = lineNumber;
		
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

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
	
	

}
