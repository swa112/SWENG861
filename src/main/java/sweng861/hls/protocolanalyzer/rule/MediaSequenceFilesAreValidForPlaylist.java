package sweng861.hls.protocolanalyzer.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.HLSUtility;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileLineInfo;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;
import sweng861.hls.protocolanalyzer.file.MediaFileType;

/**
 * Rule that enforces the correct media sequence files within a playlist file. 
 * @author Scott
 */
class MediaSequenceFilesAreValidForPlaylist extends AbstractMediaFileRule {

	
	public void runRuleCheck(HLSMediaFile file) {
		
		if (file.getFileType().equals(MediaFileType.MEDIA_PLAYLIST)){
			Pattern sequenceNumberPattern = Pattern.compile("\\d+");
			int sequence = -1;
			int targetDuration = 0;
			for (int i = 0; i<file.getFileLines().size(); i++){
				HLSMediaFileLineInfo line = file.getFileLines().get(i);
				if(line.getLineType().equals(MediaFileTagType.EXT_X_TARGET_DURATION)){
					targetDuration = determineTargetDuration(line);
				}
				if(line.getLineType().equals(MediaFileTagType.EXTINF)){
					evaluateExtInfDuration(file, targetDuration, line);
				}
				//Find first occurrence of the Media sequence or default to 0 if no media sequence is provided. 
				if(sequence == -1 && (line.getLineType().equals(MediaFileTagType.EXT_X_MEDIA_SEQUENCE) ||
					line.getLineType().equals(MediaFileTagType.TRANSPORT_STREAM_URI))){
					sequence = determineSequenceNumber(line);
				}
				if(sequence != -1){
					sequence = evaluateURIForCorrectSequence(file,
							sequenceNumberPattern, sequence, line);
					
				}
			}
		}

	}


	private int evaluateURIForCorrectSequence(HLSMediaFile file,
			Pattern sequenceNumberPattern, int sequence,
			HLSMediaFileLineInfo line) {
		if(line.getLineType().equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
			Matcher matcher = sequenceNumberPattern.matcher(line.getLineData());
			if(matcher.find()){ //Should this be while?
				String substring = line.getLineData().substring(matcher.start(), matcher.end());
				Integer actualSequence = Integer.valueOf(substring);
				if (actualSequence != sequence ){
					super.addToErrorLog(file, 
							ErrorType.MEDIA_SEGMENTS_NOT_IN_SEQUENCE, 
							String.format(ErrorType.MEDIA_SEGMENTS_NOT_IN_SEQUENCE.getMessageFormat(), 
									line.getLineData(), sequence),
							line.getLineNumber());
				}
			}
			sequence ++;
		}
		return sequence;
	}



	private int determineTargetDuration(HLSMediaFileLineInfo line) {
		int targetDuration;
		String lineData = line.getLineData();
		String value = HLSUtility.getTagValue(lineData);
		targetDuration = Double.valueOf(value).intValue();
		return targetDuration;
	}



	private int determineSequenceNumber(HLSMediaFileLineInfo line) {
		int sequence;
		if(line.getLineType().equals(MediaFileTagType.TRANSPORT_STREAM_URI)){
			sequence = 0;
		} else {
			String lineData = line.getLineData();
			String value = HLSUtility.getTagValue(lineData);
			sequence = Integer.valueOf(value);
		}
		return sequence;
	}



	private void evaluateExtInfDuration(HLSMediaFile file, int targetDuration,
			HLSMediaFileLineInfo line) {
		String lineData = line.getLineData();
		String value = HLSUtility.getTagValue(lineData, HLSConstants.ATTRIBUTE_SEPARATOR);
		Double duration = Double.valueOf(value);
		int durationRounded = (int) Math.round(duration);
		if(durationRounded != targetDuration){
			super.addToErrorLog(file,
					ErrorType.MEDIA_SEGMENT_DURATION_DOES_NOT_EQUAL_TARGET,
					String.format(ErrorType.MEDIA_SEGMENT_DURATION_DOES_NOT_EQUAL_TARGET.getMessageFormat(), 
							lineData, targetDuration),
					line.getLineNumber());
		}
	}
	
	
	
	public static void main(String[] args){
		String input = "/mediafile_10.ts";
		Pattern sequenceNumberPattern = Pattern.compile("\\d+");
		Matcher matcher = sequenceNumberPattern.matcher(input);
		if (matcher.find()){
			System.out.println(matcher.start());
			System.out.println(matcher.end());
		}
	}

}
