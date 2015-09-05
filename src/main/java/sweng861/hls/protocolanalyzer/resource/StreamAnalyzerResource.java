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
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileAnalyzerService;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileAnalyzerServiceImpl;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorLogEntry;
import sweng861.hls.protocolanalyzer.validator.ValidationErrorSeverityType;

@Path("/stream-analyzer")
public class StreamAnalyzerResource {
	
	@GET
	public Collection<HLSMediaFile> analyzeStreamResult(@QueryParam("url") @NotNull String url){
		HLSMediaFileAnalyzerService fileMediaService = new HLSMediaFileAnalyzerServiceImpl();
		List<HLSMediaFile> analyzedFiles = new ArrayList<HLSMediaFile>(); 
		try {
			analyzedFiles = fileMediaService.analyzeFiles(url);
		}catch (IOException io){
			throw new AnalyzerFailedException();
		}
//		for(HLSMediaFile file : analyzedFiles){
//			
//		}
		
//		Collection<ValidationErrorLogEntry> validationErrors = new ArrayList<ValidationErrorLogEntry>();
		ValidationErrorLogEntry log1 = new ValidationErrorLogEntry(ValidationErrorSeverityType.FATAL, "xyz rule validation failed");
		analyzedFiles.get(0).addValidationError(log1);
		//		validationErrors.add(log1);
		return analyzedFiles;
	}

}
