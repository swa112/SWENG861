package sweng861.hls.protocolanalyzer.evaluator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorLogEntry{
	
	private ErrorType error;
	
	private String fileName;
	
	private ErrorSeverityType severity; 
	
	private String message; 
	
	private String lineNumber; 
	
	
	public ErrorLogEntry(ErrorType errorType, String fileName,  String message, String lineNumber){
		this.error = errorType; 
		int lastIndexOf = fileName.lastIndexOf('/');
		this.fileName = fileName.substring(lastIndexOf +1);
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
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
	
	

}
