package be.logofac.LogoFac.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.repository.FactureRepository;

@Service
public class FactureService {
	
	private FactureRepository factureRepository;
	private PatientService patientService;
	private ProfessionnelService professionnelService;
	 

	public FactureService(FactureRepository factureRepository, PatientService patientService, ProfessionnelService professionnelService) {
		super();
		this.factureRepository = factureRepository;
		this.patientService = patientService;
		this.professionnelService = professionnelService;
	}

	public void save(Facture facture) {
		factureRepository.save(facture);
	}

	public List<Facture> findAll() {
		return factureRepository.findAll();
	}

	public List<Facture> findAllFetched() {
		List<Facture> factures = findAll();
		//Fetch the patient and the professional
		factures.forEach(n ->fetchLazyAttributes(n));
		return factures;
	}
	
	public boolean isSeanceInvoiced(Seance seance) {
		return factureRepository.countSeance(seance.getSeanceId()) > 0;
	}
	
	public void fetchLazyAttributes(Facture facture) {
		patientService.fetchLazy(facture.getPatient());
		professionnelService.fetchLazy(facture.getProfessionnel());
		//no need to fetch the lazy seances --> fetch when needed
		//facture.getSeances().forEach(x -> seanceService.fetchLazyAtributes(x));
	}
	

	public int countInvoiceInTime(LocalDate beginDate, LocalDate endDate) {

		return factureRepository.countInTime(beginDate , endDate);
		
	}
	

}
