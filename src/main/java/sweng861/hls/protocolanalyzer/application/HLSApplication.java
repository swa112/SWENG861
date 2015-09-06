package sweng861.hls.protocolanalyzer.application;

import org.glassfish.jersey.server.ResourceConfig;

public class HLSApplication extends ResourceConfig{
	public HLSApplication(){
		register(new ApplicationBinder());
        packages(true, "sweng861.hls.protocolanalyzer");
        
	}
}
