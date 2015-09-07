package sweng861.hls.protocolanalyzer;

import java.io.IOException;
import java.net.MalformedURLException;

public interface HLSMediaStreamAnalyzerService {
	
	public MediaStreamAnalyzerResult analyzeFiles(String urlStr) throws MalformedURLException, IOException;
	
}
