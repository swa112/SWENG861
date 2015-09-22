package sweng861.hls.protocolanalyzer.application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class HLSApplication extends ResourceConfig{
	public HLSApplication(){
		register(new ApplicationBinder());
		register(new MOXyJsonProvider());;
<<<<<<< HEAD
=======
//		register(new CORSFilter());
>>>>>>> cca5331a7620027ed3ed1dabe773073ed25472a3
        packages("sweng861.hls.protocolanalyzer.resource", "sweng861.hls.protocolanalyzer.application");
        
	}
}
