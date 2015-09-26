package sweng861.hls.protocolanalyzer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProtocolCompatibility {

	public int deprecatedAsOf() default 9999;
	
	public int version() default 0;
	
	public MediaFileTagValueDataType compatibleDataType() default MediaFileTagValueDataType.NONE;
}
