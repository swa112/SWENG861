/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sweng861.hls.protocolanalyzer.HLSUtility;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagValueDataType;

/**
 * @author Scott
 *
 */
public class ProtocolVersionCompatibilityRule extends AbstractMediaFileRule {


	/* (non-Javadoc)
	 * @see sweng861.hls.protocolanalyzer.rule.HLSRule#runRuleCheck(sweng861.hls.protocolanalyzer.file.HLSMediaFile)
	 */
	public void runRuleCheck(HLSMediaFile file) {
		int version = file.getVersion();
		Set<MediaFileTagValueDataType> dataTypes = new HashSet<MediaFileTagValueDataType>();
		for(HLSMediaFileLineInfo line : file.getFileLines()) {
			if(line.getLineType().equals(MediaFileTagType.EXTINF)){
				String tagValue = HLSUtility.getTagValue(line.getLineData());
				if (tagValue.matches(MediaFileTagValueDataType.EXTINF_FLOATING_POINT.getDataTypeRegEx())){
					dataTypes.add(MediaFileTagValueDataType.EXTINF_FLOATING_POINT);
				} else if (tagValue.matches(MediaFileTagValueDataType.EXTINF_INTEGER.getDataTypeRegEx())){
					dataTypes.add(MediaFileTagValueDataType.EXTINF_INTEGER);
				}
			}
		}
		
		if(dataTypes.contains(MediaFileTagValueDataType.EXTINF_FLOATING_POINT)){
			if (version < 3){
				super.addToErrorLog(file, 
						ErrorType.EXT_INF_INCOMPATIBILITY, 
						ErrorType.EXT_INF_INCOMPATIBILITY.getMessageFormat());
			}
		}
		if (dataTypes.contains(MediaFileTagValueDataType.EXTINF_INTEGER)){
			if (version > 2){
				super.addToErrorLog(file, 
						ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE, 
						ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE.getMessageFormat());
			}
		}
		// If file contains floating decimal, must be version 3. 
		//If version 3, should have floating decimal. 

	}

}
