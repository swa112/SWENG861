/**
 * 
 */
package sweng861.hls.protocolanalyzer;

/**
 * @author Scott
 *
 */
public class HLSUtility {
	
	public static String getTagValue(String lineData){
		int index = lineData.indexOf(HLSConstants.TAG_SEPARATOR);
		String value = lineData.substring(index + 1);
		return value;
	}
	
	public static String getTagValue(String lineData, String endIndexValue){
		int index = lineData.indexOf(HLSConstants.TAG_SEPARATOR);
		int endIndex = lineData.indexOf(endIndexValue);
		String value = lineData.substring(index + 1, endIndex);
		return value;
	}

}
