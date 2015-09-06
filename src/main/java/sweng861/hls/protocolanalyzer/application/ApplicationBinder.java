package sweng861.hls.protocolanalyzer.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import sweng861.hls.protocolanalyzer.file.HLSMediaFileAnalyzerService;
import sweng861.hls.protocolanalyzer.file.HLSMediaFileAnalyzerServiceImpl;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(HLSMediaFileAnalyzerService.class).to(HLSMediaFileAnalyzerServiceImpl.class);
	}

}
