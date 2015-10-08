/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import java.math.BigDecimal;
import java.util.List;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.HLSUtility;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagAttributeType;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.TransportStreamFileInfo;

/**
 * @author swa112
 *
 */
public class TSDurationMatchesBandwidth extends AbstractMediaFileRule {

	private static double TOLERANCE_LEVEL_HIGH_MULTIPLIER = 1.1;
	private static double TOLERANCE_LEVEL_LOW_MULTIPLIER = 0.9;
	
	private HLSMediaFile masterPlayList;
	
	public TSDurationMatchesBandwidth(HLSMediaFile masterPlayList) {
		this.masterPlayList = masterPlayList;
	}
	
	/* (non-Javadoc)
	 * @see sweng861.hls.protocolanalyzer.rule.HLSRule#runRuleCheck(sweng861.hls.protocolanalyzer.file.HLSMediaFile)
	 */
	public void runRuleCheck(HLSMediaFile file) {
		String fileName = HLSUtility.getFileNameFromURL(file.getFileName());
		HLSMediaFileLineInfo lineInfo = getLineFromMasterPlaylist(fileName, file);
		if(lineInfo != null ){
			Double bandwidth = Double.valueOf(determineBandwidth(lineInfo));
			Double high = bandwidth * TOLERANCE_LEVEL_HIGH_MULTIPLIER;
			Double low = bandwidth * TOLERANCE_LEVEL_LOW_MULTIPLIER;
			List<TransportStreamFileInfo> tsFiles = file.getTsFiles();
			for (TransportStreamFileInfo tsFile : tsFiles){
				BigDecimal bitRate = tsFile.getCalculatedBitRate();
				Double bitRateDbl = bitRate.doubleValue();
				if (!(high > bitRateDbl && bitRateDbl > low)){
					String tsName = HLSUtility.getFileNameFromURL(tsFile.getFileName());
					super.addToErrorLog(file, 
							ErrorType.BANDWIDTH_OUT_OF_TOLERANCE, 
							String.format(ErrorType.BANDWIDTH_OUT_OF_TOLERANCE.getMessageFormat(), bitRateDbl.intValue(), bandwidth.intValue()),
							tsName);
							
				}
			}
		}

	}
	
	private HLSMediaFileLineInfo getLineFromMasterPlaylist(String name, HLSMediaFile file){
		List<HLSMediaFileLineInfo> fileLines = masterPlayList.getFileLines();
		int index = file.getFileName().lastIndexOf(HLSConstants.SLASH);
		String fileName = file.getFileName().substring(index +1);
		
		for (int i =0; i < fileLines.size(); i++){
			if(fileLines.get(i).getLineType().equals(MediaFileTagType.EXT_X_STREAM_INF)){
				String nextLine = fileLines.get(i+1).getLineData();
			
				if(nextLine.equals(fileName)){
					return fileLines.get(i);
				}
			}
		}
		return null;
	}
	
	private int determineBandwidth(HLSMediaFileLineInfo lineInfo){
		String[] attributes = lineInfo.getLineData().split(HLSConstants.ATTRIBUTE_SEPARATOR);

		for (String attribute : attributes){
			String[] nameValue = attribute.split(HLSConstants.NAME_VALUE_SEPARATOR); //length should be 2. 
			MediaFileTagAttributeType attributeType = MediaFileTagAttributeType.getAttributeTypeFromString(nameValue[0].trim());
			if(attributeType.equals(MediaFileTagAttributeType.BANDWIDTH)){
				return Integer.parseInt(nameValue[1]);
			}
		
		}
		return 0;
	}

}
