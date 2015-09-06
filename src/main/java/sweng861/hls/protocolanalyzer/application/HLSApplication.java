package sweng861.hls.protocolanalyzer.application;

import org.glassfish.jersey.server.ResourceConfig;

public class HLSApplication extends ResourceConfig{
	public HLSApplication(){
		register(new ApplicationBinder());
        packages("sweng861.hls.protocolanalyzer.resource", "sweng861.hls.protocolanalyzer.file");
        
	}
}
