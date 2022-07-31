package be.logofac.LogoFac;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.AppParameter;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.domain.AppParameterGeneral;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Prix;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.ParameterReference;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;
import be.logofac.LogoFac.service.AdresseService;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.ParameterService;
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
	private ParameterService parameterService;
	
	
	
	

	public InitialLoad(PrixService prixService, ProfessionnelService professionnelService,
			PatientService patientService, SeanceService seanceService, AdresseService adresseService,
			FactureService factureService, ParameterService parametereService) {
		super();
		this.prixService = prixService;
		this.professionnelService = professionnelService;
		this.patientService = patientService;
		this.seanceService = seanceService;
		this.adresseService = adresseService;
		this.factureService = factureService;
		this.parameterService = parametereService;
	}

	public void initialLoad() {
		fillParameterTable();
		fillPrice();
		fillProfessionnal();
		fillPatient();
		
		fillSeance();
		fillFacture();
	}
	
	private void fillParameter() {
		AppParameterGeneral appParameterGeneral = new AppParameterGeneral();
		appParameterGeneral.setParameterReference(ParameterReference.FOLDER_LOCATION);
		appParameterGeneral.setValue("D:\\Users\\samyd\\Documents");
		parameterService.save(appParameterGeneral);
		appParameterGeneral = new AppParameterGeneral();
		appParameterGeneral.setParameterReference(ParameterReference.INVOICE_OFFSET);
		appParameterGeneral.setValue(0);
		parameterService.save(appParameterGeneral);
		
		
	}

	private void fillParameterTable() {
		fillParameter(); 
		
		AppParameterAmount appParameterAmount = new AppParameterAmount(28.33, "Prix seance demi-heure au cabinet ", SeanceType.Cabinet, SeanceDuration.demi_heure);
		parameterService.save(appParameterAmount);
		appParameterAmount = new AppParameterAmount(28.33, "Prix seance demi-heure au domicile ", SeanceType.Domicile, SeanceDuration.demi_heure);
		parameterService.save(appParameterAmount);
		appParameterAmount = new AppParameterAmount(28.33, "Prix seance demi-heure en ligne ", SeanceType.Online, SeanceDuration.demi_heure);
		parameterService.save(appParameterAmount);
		appParameterAmount = new AppParameterAmount(56.89, "Prix seance une heure au cabinet ", SeanceType.Cabinet, SeanceDuration.heure);
		parameterService.save(appParameterAmount);
		appParameterAmount = new AppParameterAmount(56.89, "Prix seance une heure en ligne ", SeanceType.Online, SeanceDuration.heure);
		parameterService.save(appParameterAmount);
		appParameterAmount = new AppParameterAmount(56.89, "Prix seance une heure au domicile ", SeanceType.Domicile, SeanceDuration.heure);
		parameterService.save(appParameterAmount);
		
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
		Facture facture = new Facture( "Reference",  false , patient, professionel,"Communication ", LocalDate.now(), LocalDate.now(), seances);
		
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
				Seance seance = new Seance( patient.get(), professionnel.get(), LocalDateTime.of(LocalDate.of(2021, 12, 1), LocalTime.of(14, 30)), SeanceDuration.demi_heure, SeanceType.Cabinet, false, false
						);
	   			seanceService.save(seance);
				seanceService.findAllSeance().forEach(n -> System.out.println(n.toString()));
			}
		}
	}

	
	private void fillPatient() {
		Adresse adresse = new Adresse("patient adresse", "Patient Service ", "11", 1000, "Patient Land");
		Patient patient = new Patient("Pat", "ient", LocalDate.of(1992, 05, 14), "samy.Daou@hotmail.com",adresse, false);
		patientService.save(patient);
		patient = new Patient("Patout", "rien",LocalDate.of(1993, 8, 12), "marchovnikov@gmail.com", adresse, false);
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
