package sweng861.hls.protocolanalyzer.application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class HLSApplication extends ResourceConfig{
	public HLSApplication(){
		register(new ApplicationBinder());
		register(new MOXyJsonProvider());;
		register(new CORSFilter());
        packages("sweng861.hls.protocolanalyzer.resource", "sweng861.hls.protocolanalyzer.application");
        
	}
}
