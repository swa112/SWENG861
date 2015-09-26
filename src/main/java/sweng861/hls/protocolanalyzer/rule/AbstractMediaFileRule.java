package sweng861.hls.protocolanalyzer.rule;

import sweng861.hls.protocolanalyzer.evaluator.ErrorLogEntry;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;

public abstract class AbstractMediaFileRule implements HLSRule {

	private static final int FILE_LEVEL = 0;

	protected void addToErrorLog(HLSMediaFile file, ErrorType error, String message){
		addToErrorLog(file, error, message, FILE_LEVEL);
	}
	
	protected void addToErrorLog(HLSMediaFile file, ErrorType error, String message, int lineNumber){
		ErrorLogEntry entry = new ErrorLogEntry(
				error, file.getFileName(), message, lineNumber);
		file.addValidationError(entry);
	}
//	
//	protected void addConsolidatedRuleToErrorLog(HLSMediaFile file, ErrorType error, String message){
//		if(!doesErrorTypeExist(file, error)){
//			ErrorLogEntry entry = new ErrorLogEntry(
//					error, file.getFileName(), message, 0);
//			file.addValidationError(entry);
//		}
//		
//	}
//	
//	private boolean doesErrorTypeExist(HLSMediaFile file, ErrorType error){
//		for(ErrorLogEntry entry : file.getValidationErrors()){
//			if(entry.getError().equals(error)){
//				return true;
//			}
//		}
//		return false;
//	}

}
