package sweng861.hls.protocolanalyzer.application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Jersey configuration for JSON and resources. 
 * @author swa112
 *
 */
public class HLSApplication extends ResourceConfig{
	public HLSApplication(){
		register(new MOXyJsonProvider());;
        packages("sweng861.hls.protocolanalyzer.resource", "sweng861.hls.protocolanalyzer.application");
        
	}
}
