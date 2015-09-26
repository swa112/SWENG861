package sweng861.hls.protocolanalyzer.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sweng861.hls.protocolanalyzer.HLSConstants;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerService;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerServiceImpl;
import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

@Path("/stream-analyzer")
public class StreamAnalyzerResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ErrorLogEntry> analyzeStreamResult(@QueryParam("url") @NotNull String url){

		HLSMediaStreamAnalyzerService fileMediaService = new HLSMediaStreamAnalyzerServiceImpl();
		MediaStreamAnalyzerResult result = null;
		List<ErrorLogEntry> errorList = new ArrayList<ErrorLogEntry>();
		try {
			result = fileMediaService.analyzeFiles(url);
		}catch (Exception e){ //io should be refactored. 
			errorList.add(new ErrorLogEntry(ErrorType.GENERIC_ERROR, "Invalid URL", ErrorType.GENERIC_ERROR.getMessageFormat(), HLSConstants.FILE_LEVEL));
		}
		if(result != null){
			errorList.addAll(result.getErrors());
		
			List<HLSMediaFile> files = result.getFiles();
			for(HLSMediaFile file : files){
				errorList.addAll(file.getValidationErrors());
			}
		}
	

		return errorList;
	}
	
	

}
