package sweng861.hls.protocolanalyzer.file;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HLSMediaFile  {
	@XmlElement
	private String fileName;
	
	@XmlElement
	private MediaFileType fileType; 
	
	private List<HLSMediaFileLineInfo> fileLines;
	
	private List<TransportStreamFileInfo> tsFiles = new ArrayList<TransportStreamFileInfo>();
	
	@XmlElement
	private List<ErrorLogEntry> validationErrors = new ArrayList<ErrorLogEntry>();
	
	private List<MediaFileTagType> tagList;
	
	private int version = 1; //default to version 1 if version is not specified. 


	public HLSMediaFile(String fileName){
		this.fileName = fileName;
	}
	
	public HLSMediaFile(){
		//default constructor for JAXB
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MediaFileType getFileType() {
		return fileType;
	}

	public void setFileType(MediaFileType fileType) {
		this.fileType = fileType;
	}

	public List<HLSMediaFileLineInfo> getFileLines() {
		return fileLines;
	}
	public void setFileLines(List<HLSMediaFileLineInfo> fileLines) {
		this.fileLines = fileLines;
	}

	public void addFileLine(HLSMediaFileLineInfo line) {
		if(this.fileLines == null){
			this.fileLines = new ArrayList<HLSMediaFileLineInfo>();
		}
		this.fileLines.add(line);
		addTag(line.getLineType());
	}
	
	public List<ErrorLogEntry> getValidationErrors() {
		return this.validationErrors;
	}
	
	public void addValidationError(ErrorLogEntry validationError){
		boolean isAdd = true;
		if(validationError.getError().equals(ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE)){
			for(ErrorLogEntry entry : validationErrors){
				if (entry.getError().equals(ErrorType.USE_OF_DEPRECATED_TAG_VALUE_TYPE)){
					entry.setLineNumber("Multiple");
					isAdd = false;
					break;
				}
			}
		} 
		if(isAdd){
			this.validationErrors.add(validationError);
		}
	}

	public void setValidationErrors(List<ErrorLogEntry> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	public List<MediaFileTagType> getTagList() {
		return tagList;
	}


	
	void addTag(MediaFileTagType tag) {
		if(this.tagList == null){
			tagList = new ArrayList<MediaFileTagType>();
		}
		this.tagList.add(tag);
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<TransportStreamFileInfo> getTsFiles() {
		return tsFiles;
	}

	public void addTsFiles(TransportStreamFileInfo tsFile) {
		tsFiles.add(tsFile);
		
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("File: " + fileName);
		for (HLSMediaFileLineInfo line: fileLines){
			sb.append("\t" + line);
		}
		return sb.toString();
	}
	
	

}
