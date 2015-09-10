package sweng861.hls.protocolanalyzer;

/**
 * Generic runtime exception for any unhandled errors. 
 * @author Scott
 *
 */
public class AnalyzerFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AnalyzerFailedException(String message, Exception e){
		super(message, e);
	}
}
