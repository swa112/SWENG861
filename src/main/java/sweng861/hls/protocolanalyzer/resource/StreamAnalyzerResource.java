package sweng861.hls.protocolanalyzer.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import sweng861.hls.protocolanalyzer.AnalyzerFailedException;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerService;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerServiceImpl;
import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

@Path("/stream-analyzer")
public class StreamAnalyzerResource {
	
//	@Inject
//	private HLSMediaFileAnalyzerService fileMediaService;
	
	@GET
	public MediaStreamAnalyzerResult analyzeStreamResult(@QueryParam("url") @NotNull String url){
		HLSMediaStreamAnalyzerService fileMediaService = new HLSMediaStreamAnalyzerServiceImpl();
		MediaStreamAnalyzerResult result = null;
		try {
			result = fileMediaService.analyzeFiles(url);
		}catch (IOException io){
			throw new AnalyzerFailedException();
		}

		return result;
	}

}
