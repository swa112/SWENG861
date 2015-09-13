package sweng861.hls.protocolanalyzer.evaluator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorLogEntry{
	
	private ErrorSeverityType errorType; 
	
	private String message; 
	
	private int lineNumber; 
	
	
	public ErrorLogEntry(ErrorSeverityType errorType,  String message, int lineNumber){
		this.errorType = errorType; 
		this.message = message; 
		this.lineNumber = lineNumber;
		
	}
	
	public ErrorLogEntry(){
		
	}

	public ErrorSeverityType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorSeverityType errorType) {
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
