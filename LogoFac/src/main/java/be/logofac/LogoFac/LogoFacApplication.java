package be.logofac.LogoFac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class })
public class LogoFacApplication implements CommandLineRunner{
	
	private InitialLoad initialLoad;

	public LogoFacApplication(InitialLoad initialLoad) {
		super();
		this.initialLoad = initialLoad;
	}

	public static void main(String[] args) {
		SpringApplication.run(LogoFacApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		
		initialLoad.initialLoad();
	}

}
