package sweng861.hls.protocolanalyzer.file;

public class HLSMediaFileLineInfo implements HLSMediaEntity {
	
	private String lineData;
	
	private MediaFileTagType lineType;
	
	private int lineNumber;

	public String getLineData() {
		return lineData;
	}

	public void setLineData(String lineData) {
		this.lineData = lineData;
	}

	public MediaFileTagType getLineType() {
		return lineType;
	}

	public void setLineType(MediaFileTagType lineType) {
		this.lineType = lineType;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	

}
