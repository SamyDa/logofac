package be.logofac.LogoFac;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Prix;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;
import be.logofac.LogoFac.service.AdresseService;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.PatientService;
import be.logofac.LogoFac.service.PrixService;
import be.logofac.LogoFac.service.ProfessionnelService;
import be.logofac.LogoFac.service.SeanceService;
@Component
public class InitialLoad {
	
	private PrixService prixService;
	private ProfessionnelService professionnelService;
	private PatientService patientService;
	private SeanceService seanceService;
	private AdresseService adresseService;
	private FactureService factureService;
	
	
	
	

	public InitialLoad(PrixService prixService, ProfessionnelService professionnelService,
			PatientService patientService, SeanceService seanceService, AdresseService adresseService,
			FactureService factureService) {
		super();
		this.prixService = prixService;
		this.professionnelService = professionnelService;
		this.patientService = patientService;
		this.seanceService = seanceService;
		this.adresseService = adresseService;
		this.factureService = factureService;
	}

	public void initialLoad() {
		fillPrice();
		fillProfessionnal();
		fillPatient();
		fillSeance();
		fillFacture();
	}
	
	private void fillFacture() {
		Patient patient = new Patient();
		Professionnel professionel = null;
		if(patientService.findAll().stream().findFirst().isPresent())
			patient = patientService.findAll().stream().findFirst().get();
		if(professionnelService.findAllPro().stream().findFirst().isPresent())
			professionel = professionnelService.findAllPro().stream().findFirst().get();
		ArrayList<Seance> seances = new ArrayList<Seance>();
		seanceService.findAllSeance().forEach(n -> seances.add(n));
		Facture facture = new Facture( "Reference",  false , patient, professionel,"Communication ", LocalDate.now(), seances);
		
		factureService.save(facture);
		
		factureService.findAllFetched().forEach(n-> {System.out.println(n.toString());});
		
	}

	private void fillProfessionnal() {
		Adresse adresse = new Adresse( "Rue des testers ", "Tester city", "1A", 7500, "Tester Land"); 
		Professionnel pro = new Professionnel( "Test", "TesterName", "BCE number", adresse, "Testeur", "+32488182809", "tester@testr.com", "Inami tester", "BE TESTER", "BIC Tester");
		professionnelService.save(pro);
		
		professionnelService.findAllPro().forEach(n -> System.out.println(n.toString()));
		
	}


	
	private void fillSeance() {
		
		Optional <Patient> patient = patientService.findAll().stream().findFirst();
		if(patient.isPresent()) {
			Optional<Professionnel> professionnel = professionnelService.findAllPro().stream().findFirst();
			if (professionnel.isPresent()) {
				Seance seance = new Seance( patient.get(), professionnel.get(), LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(14, 30)), SeanceDuration.demi_heure, SeanceType.Cabinet
						);
	   			seanceService.save(seance);
				seanceService.findAllSeance().forEach(n -> System.out.println(n.toString()));
			}
		}
	}

	
	private void fillPatient() {
		Adresse adresse = new Adresse("patient adresse", "Patient Service ", "11", 1000, "Patient Land");
		Patient patient = new Patient("Pat", "ient", adresse);
		patientService.save(patient);
		patient = new Patient("Patout", "rien", adresse);
		patientService.save(patient);
		patientService.findAll().forEach(n -> System.out.println(n.toString()) );
		
		
	}

	private void fillPrice() {
		Prix price = new Prix( LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31), 24.2);
		changePrice(price);
		prixService.save(price);
		System.out.println("List of price is  : " );
		prixService.getAllPrices().forEach(n -> System.out.println(" -> " + n.toString()));
		
	}

	private void changePrice(Prix price) {
		 price = new Prix( LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31), 27.2);
		
	}

}
