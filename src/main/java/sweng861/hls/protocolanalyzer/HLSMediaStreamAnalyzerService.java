package sweng861.hls.protocolanalyzer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public interface HLSMediaStreamAnalyzerService {
	
	public List<HLSMediaFile> analyzeFiles(String urlStr) throws MalformedURLException, IOException;
	
}
