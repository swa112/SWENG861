package sweng861.hls.protocolanalyzer.evaluator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorLogEntry{
	
	private ErrorType error;
	
	private ErrorSeverityType severity; 
	
	private String message; 
	
	private int lineNumber; 
	
	
	public ErrorLogEntry(ErrorType errorType,  String message, int lineNumber){
		this.error = errorType; 
		this.severity = errorType.getSeverity();
		this.message = message; 
		this.lineNumber = lineNumber;
		
	}
	
	public ErrorLogEntry(){
		
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}
	
	
	public ErrorSeverityType getSeverity() {
		return severity;
	}

	public void setSeverity(ErrorSeverityType severity) {
		this.severity = severity;
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
