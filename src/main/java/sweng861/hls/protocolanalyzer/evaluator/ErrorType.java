package sweng861.hls.protocolanalyzer.evaluator;

public enum ErrorType {
	
	USE_OF_DEPRECATED_ATTRIBUTE(ErrorSeverityType.INFO, "Attribute [%s] is deprecated as of %s"),
	
	MEDIA_SEGMENTS_NOT_IN_SEQUENCE(ErrorSeverityType.FATAL, "Media Segment [%s] did not match expected sequence of [%d]"),
	
	TAG_HAS_INVALID_NUMBER_OF_OCCURENCES (ErrorSeverityType.FATAL, "Tag [%s] was found %d times, but it must occur [%s]"),
	
	INVALID_CONTENT_TYPE_HEADER(ErrorSeverityType.FATAL, "HTTP Content-Type header [%s] is invalid."),
	
	MEDIA_SEGMENT_DURATION_DOES_NOT_EQUAL_TARGET(ErrorSeverityType.WARNING, "Media Segment [%s] does match target duration of %d"),
	
	COMMENT_FOUND(ErrorSeverityType.INFO, "Found comment [%s], ensure that this is intended to comment and not a misformed tag."),
	
	MISSING_REQUIRED_TAG_FORMAT(ErrorSeverityType.FATAL, "Required Tag [%s] was not found in file type %s"),
	
	INVALID_TAG_FOR_FILE (ErrorSeverityType.FATAL, "Found tag [%s] that is not allowed in file type %s"),
	
	FOUND_IMPROPER_TAG (ErrorSeverityType.WARNING, "Found tag [%s] that does not match known tag types."),
	
	MISSING_START_TAG (ErrorSeverityType.FATAL, "Start tag for file type %s [%s] was not found"),
	
	INVALID_FILE_TYPE (ErrorSeverityType.FATAL, "File [%s] is not a recognized file type"),

	MISSING_FOLLOWING_TAG  (ErrorSeverityType.FATAL, "The tag [%s] is not followed by the required type."),

	;
	private ErrorSeverityType severity; 
	
	private String messageFormat; 
	
	private ErrorType(ErrorSeverityType severity, String messageFormat){
		
		this.severity = severity;
		this.messageFormat = messageFormat; 
		
	}
	
	public ErrorSeverityType getSeverity(){
		return severity; 
	}
	
	public String getMessageFormat(){
		return messageFormat;
	}
}
