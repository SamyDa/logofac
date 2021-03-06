package be.logofac.LogoFac;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.SeanceService;
import javafx.stage.Stage;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class })
public class LogoFacApplication implements CommandLineRunner{
	
	private InitialLoad initialLoad;
	private  DocumentProcess documentProcess;
	private FactureService factureService;
	private FrontApp app;

	
	

	public LogoFacApplication(InitialLoad initialLoad, DocumentProcess documentProcess, FactureService factureService,
			FrontApp app) {
		super();
		this.initialLoad = initialLoad;
		this.documentProcess = documentProcess;
		this.factureService = factureService;
		this.app = app;
	}

	public static void main(String[] args) {
		SpringApplication.run(LogoFacApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		//testDocument.createDocument();
		//initialLoad.initialLoad();
		Optional<Facture> facture = factureService.findAllFetched().stream().findFirst();
		if(facture.isPresent())
		{
			documentProcess.loadDocumentData(facture.get());
		}
		LocalDate beginDate = LocalDate.of(2020, 1, 1);
		LocalDate endDate = LocalDate.of(2020, 12, 31);
		System.out.println("Count invoice 2020 = " + factureService.countInvoiceInTime(beginDate , endDate));
		beginDate = LocalDate.of(2019, 1, 1);
		endDate = LocalDate.of(2019, 12, 31);
		System.out.println("Count invoice 2019 = " + factureService.countInvoiceInTime(beginDate , endDate));
		
		new Thread(){
            public void run() {
                app.main(new String[]{});}
                }.start();
    	}
		
	

}
