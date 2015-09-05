package sweng861.hls.protocolanalyzer.file;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HLSMediaFile {
	@XmlElement
	private String fileName;
	
	private List<String> fileLines;
	
	@XmlElement
	private List<ValidationErrorLogEntry> validationErrors = new ArrayList<ValidationErrorLogEntry>();
	
	public HLSMediaFile(String fileName){
		this.fileName = fileName;
	}
	
	public HLSMediaFile(){
		
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileLines() {
		return fileLines;
	}
	public void setFileLines(List<String> fileLines) {
		this.fileLines = fileLines;
	}

	public void addFileLine(String line) {
		if(this.fileLines == null){
			this.fileLines = new ArrayList<String>();
		}
		this.fileLines.add(line);
	}
	
	public List<ValidationErrorLogEntry> getValidationErrors() {
		return this.validationErrors;
	}
	
	public void addValidationError(ValidationErrorLogEntry validationError){
		this.validationErrors.add(validationError);
	}

	public void setValidationErrors(List<ValidationErrorLogEntry> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("File: " + fileName);
		for (String line: fileLines){
			sb.append("\t" + line);
		}
		return sb.toString();
	}
	
	

}
