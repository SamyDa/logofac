package be.logofac.LogoFac.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceCatalog {

	private AdresseService adresseService;
	private FactureService factureService;
	private PatientService patientService;
	private PrixService prixService;
	private ProfessionnelService professionnelService;
	private SeanceService seanceService;
	private ParameterService parameterService;
	public ServiceCatalog(AdresseService adresseService, FactureService factureService, PatientService patientService,
			PrixService prixService, ProfessionnelService professionnelService, SeanceService seanceService, ParameterService parameterService) {
		super();
		this.adresseService = adresseService;
		this.factureService = factureService;
		this.patientService = patientService;
		this.prixService = prixService;
		this.professionnelService = professionnelService;
		this.seanceService = seanceService;
		this.parameterService = parameterService;
	}
	
	public AdresseService getAdresseService() {
		return adresseService;
	}
	public FactureService getFactureService() {
		return factureService;
	}
	public PatientService getPatientService() {
		return patientService;
	}
	public PrixService getPrixService() {
		return prixService;
	}
	public ProfessionnelService getProfessionnelService() {
		return professionnelService;
	}
	public SeanceService getSeanceService() {
		return seanceService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}
	
	
}
