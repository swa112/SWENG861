package sweng861.hls.protocolanalyzer.evaluator;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ErrorType {
	
	@XmlEnumValue("Found use of deprecated protocol")
	USE_OF_DEPRECATED_PROTOCOL(ErrorSeverityType.INFO, "The tag or attribute [%s] is deprecated as of %s"),
	
	@XmlEnumValue("Found error in media segments, not in sequence")
	MEDIA_SEGMENTS_NOT_IN_SEQUENCE(ErrorSeverityType.FATAL, "Media Segment [%s] did not match expected sequence of [%d]"),
	
	@XmlEnumValue("Tag has invalid number of occurences")
	TAG_HAS_INVALID_NUMBER_OF_OCCURENCES (ErrorSeverityType.FATAL, "Tag [%s] was found %d times but it must occur [%s]"),
	
	@XmlEnumValue("Found invalid content-type header")
	INVALID_CONTENT_TYPE_HEADER(ErrorSeverityType.FATAL, "HTTP Content-Type header [%s] is invalid."),
	
	@XmlEnumValue("Media segment does not match the target")
	MEDIA_SEGMENT_DURATION_DOES_NOT_EQUAL_TARGET(ErrorSeverityType.WARNING, "Media Segment [%s] does match target duration of %d"),
	
	COMMENT_FOUND(ErrorSeverityType.INFO, "Found comment [%s] ensure that this is intended to comment and not a misformed tag."),
	
	@XmlEnumValue("Missing required tag")
	MISSING_REQUIRED_TAG(ErrorSeverityType.FATAL, "Required Tag [%s] was not found in file type %s"),
	
	@XmlEnumValue("Tag is invalid for file type")
	INVALID_TAG_FOR_FILE (ErrorSeverityType.FATAL, "Found tag [%s] that is not allowed in file type %s"),
	
	@XmlEnumValue("Found a tag that does not match known types")
	FOUND_IMPROPER_TAG (ErrorSeverityType.WARNING, "Found tag [%s] that does not match known tag types."),
	
	@XmlEnumValue("The start tag for file is missing")
	MISSING_START_TAG (ErrorSeverityType.FATAL, "Start tag for file type %s [%s] was not found"),
	
	@XmlEnumValue("Found an invalid file type")
	INVALID_FILE_TYPE (ErrorSeverityType.FATAL, "File [%s] is not a recognized file type"),

	@XmlEnumValue("The content following a tag is missing")
	MISSING_FOLLOWING_TAG  (ErrorSeverityType.FATAL, "The tag [%s] is not followed by the required type."),
	
	@XmlEnumValue("Tag is not formatted with proper data type")
	TAG_FORMAT_ERROR(ErrorSeverityType.FATAL, "Tag [%s] is not formatted with the proper data type. Value = [%s]"),
	
	@XmlEnumValue("Tag is missing colon separater")
	TAG_MISSING_COLON (ErrorSeverityType.FATAL, "Could not determine data type for tag [%s] because : could not be found"),
	
	@XmlEnumValue("Attribute has invalid data type")
	INVALID_ATTRIBUTE_DATA_TYPE (ErrorSeverityType.WARNING, "Attribue [%s] data type does not match. Value = [%s]"),
	
	@XmlEnumValue("Attribute was not found")
	ATTRIBUTE_NOT_FOUND (ErrorSeverityType.WARNING, "Attribue [%s] was not recognized for tag [%s]"),
	
	@XmlEnumValue("Invalid URI found in playlist")
	INVALID_URI_FOUND(ErrorSeverityType.FATAL, "URL [%s] was not found or found invalid text in the file."),

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
