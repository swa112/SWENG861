package sweng861.hls.protocolanalyzer.validator;

public enum ValidationErrorType {
	
	USE_OF_DEPRECATED_ATTRIBUTE(ValidationErrorSeverityType.INFO, "Attribute [%s] is deprecated as of %s"),
	
	MEDIA_SEGMENTS_NOT_IN_SEQUENCE(ValidationErrorSeverityType.FATAL, "Media Segment [%s] did not match expected sequence of [%d]"),
	
	TAG_THAT_MUST_BE_UNIQUE_APPERS_MORE_THAN_ONCE (ValidationErrorSeverityType.FATAL, "Tag [%s] was found %d times, but it can only have a signle occurence."),
	
	INVALID_CONTENT_TYPE_HEADER(ValidationErrorSeverityType.FATAL, "HTTP Content-Type header [%s] is invalid."),
	
	;
	private ValidationErrorSeverityType severity; 
	
	private String messageFormat; 
	
	private ValidationErrorType(ValidationErrorSeverityType severity, String messageFormat){
		
		this.severity = severity;
		this.messageFormat = messageFormat; 
		
	}
	
	public ValidationErrorSeverityType getSeverity(){
		return severity; 
	}
	
	public String getMessageFormat(){
		return messageFormat;
	}
}
