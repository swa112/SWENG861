/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import java.math.BigDecimal;
import java.util.List;

import sweng861.hls.protocolanalyzer.HLSUtility;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.TransportStreamFileInfo;

/**
 * @author swa112
 *
 */
public class TSDurationMatchesBandwidth implements HLSRule {

	private static double TOLERANCE_LEVEL = 0.1;
	
	private HLSMediaFile masterPlayList;
	
	public TSDurationMatchesBandwidth(HLSMediaFile masterPlayList) {
		this.masterPlayList = masterPlayList;
	}
	
	/* (non-Javadoc)
	 * @see sweng861.hls.protocolanalyzer.rule.HLSRule#runRuleCheck(sweng861.hls.protocolanalyzer.file.HLSMediaFile)
	 */
	public void runRuleCheck(HLSMediaFile file) {
		String fileName = HLSUtility.getFileNameFromURL(file.getFileName());
		HLSMediaFileLineInfo lineInfo = getLineFromMasterPlaylist(fileName);
//		lineInfo.getLineData()
		// TODO Auto-generated method stub
		List<TransportStreamFileInfo> tsFiles = file.getTsFiles();
		for (TransportStreamFileInfo tsFile : tsFiles){
			BigDecimal duration = tsFile.getDuration();
			
		}

	}
	
	private HLSMediaFileLineInfo getLineFromMasterPlaylist(String name){
		List<HLSMediaFileLineInfo> fileLines = masterPlayList.getFileLines();
		for (HLSMediaFileLineInfo fileLine: fileLines){
			if(fileLine.getLineType().equals(MediaFileTagType.EXT_X_STREAM_INF)){
				return fileLine;
			}
		}
		return null;
	}

}
