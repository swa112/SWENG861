package sweng861.hls.protocolanalyzer.file;

import java.util.ArrayList;
import java.util.List;

public class HLSMediaFile {
	
	private String fileName;
	
	private List<String> fileLines;
	
	public HLSMediaFile(String fileName){
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}


	public List<String> getFileLines() {
		return fileLines;
	}

	public void addFileLine(String line) {
		if(this.fileLines == null){
			this.fileLines = new ArrayList<String>();
		}
		this.fileLines.add(line);
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
