package sweng861.hls.protocolanalyzer.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface HLSMediaFileAnalyzerService {
	
	public List<HLSMediaFile> analyzeFiles(String urlStr) throws MalformedURLException, IOException;
	
}
