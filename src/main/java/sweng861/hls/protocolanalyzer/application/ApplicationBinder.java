package sweng861.hls.protocolanalyzer.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerService;
import sweng861.hls.protocolanalyzer.HLSMediaStreamAnalyzerServiceImpl;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(HLSMediaStreamAnalyzerService.class).to(HLSMediaStreamAnalyzerServiceImpl.class);
	}

}
