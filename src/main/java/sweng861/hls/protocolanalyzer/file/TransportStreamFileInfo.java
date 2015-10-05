package sweng861.hls.protocolanalyzer.file;

import java.math.BigDecimal;

public class TransportStreamFileInfo {
	
	private String fileName; 
	
	private BigDecimal duration;
	
	private BigDecimal calculatedBitRate;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getCalculatedBitRate() {
		return calculatedBitRate;
	}

	public void setCalculatedBitRate(BigDecimal calculatedBitRate) {
		this.calculatedBitRate = calculatedBitRate;
	}
	
	

}
