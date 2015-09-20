package sweng861.hls.protocolanalyzer.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sweng861.hls.protocolanalyzer.AnalyzerFailedException;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerService;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerServiceImpl;
import sweng861.hls.protocolanalyzer.MediaStreamAnalyzerResult;
import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

@Path("/stream-analyzer")
public class StreamAnalyzerResource {
	
//	@Inject
//	private HLSMediaFileAnalyzerService fileMediaService;
	
//	@OPTIONS
//	public Response options(){
//		return Response.status(200)
//		         .header("Access-Control-Allow-Origin", "*")
//		         .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//		         .header("Access-Control-Allow-Credentials", "true")
//		         .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//		         .header("Access-Control-Max-Age", "1209600")
//		         .build();
//	}
//	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ErrorLogEntry> analyzeStreamResult(@QueryParam("url") @NotNull String url){
//	public Response analyzeStreamResult(@QueryParam("url") @NotNull String url){
		HLSMediaStreamAnalyzerService fileMediaService = new HLSMediaStreamAnalyzerServiceImpl();
		MediaStreamAnalyzerResult result = null;
		List<ErrorLogEntry> errorList = new ArrayList<ErrorLogEntry>();
		try {
			result = fileMediaService.analyzeFiles(url);
		}catch (IOException io){ //io should be refactored. 
			throw new AnalyzerFailedException("Analyzer failed with exception: ", io);
		}
		errorList.addAll(result.getErrors());
		List<HLSMediaFile> files = result.getFiles();
		for(HLSMediaFile file : files){
			errorList.addAll(file.getValidationErrors());
		}
		GenericEntity<List<ErrorLogEntry>> entity = new GenericEntity<List<ErrorLogEntry>>(errorList){};
//		return errorList;
		//ADD CORS Header
//		return Response.status(200)
//         .header("Access-Control-Allow-Origin", "*")
//         .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//         .header("Access-Control-Allow-Credentials", "true")
//         .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//         .header("Access-Control-Max-Age", "1209600")
//         .entity(entity)
//         .build();
		return errorList;
	}
	
	
	@GET
	@Path("/logs")
	public MediaStreamAnalyzerResult analyzeStreamResult(){
		return null;
	}

}
