package be.logofac.LogoFac;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import be.logofac.LogoFac.Utils.DocumentProcess;
import be.logofac.LogoFac.Utils.ParamRetriever;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.SeanceService;
import be.logofac.LogoFac.service.ServiceCatalog;
import javafx.stage.Stage;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class })
public class LogoFacApplication implements CommandLineRunner{
	
	private InitialLoad initialLoad;
	private  DocumentProcess documentProcess;
	private FactureService factureService;
	private FrontApp app;
	private ServiceCatalog serviceCatalog;

	
	

	public LogoFacApplication(InitialLoad initialLoad, DocumentProcess documentProcess, FactureService factureService,
			FrontApp app, ServiceCatalog serviceCatalog) {
		super();
		this.initialLoad = initialLoad;
		this.documentProcess = documentProcess;
		this.factureService = factureService;
		this.app = app;
		this.serviceCatalog = serviceCatalog;
	}

	public static void main(String[] args) {
		SpringApplication.run(LogoFacApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		
		
		initialLoad.initialLoad();
		
		new Thread(){
            public void run() {
            	FrontApp.serviceCatalog = serviceCatalog;
                FrontApp.main(new String[]{});}
                }.start();
                
    	}
		
}
