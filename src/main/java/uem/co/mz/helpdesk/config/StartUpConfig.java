package uem.co.mz.helpdesk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:applicationContext.xml" })
public class StartUpConfig {
	
	public StartUpConfig(){
		super();
	}

}
