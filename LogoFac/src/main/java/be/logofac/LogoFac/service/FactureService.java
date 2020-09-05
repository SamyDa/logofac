package be.logofac.LogoFac.service;

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
	private SeanceService seanceService;


	public FactureService(FactureRepository factureRepository, PatientService patientService,
			SeanceService seanceService) {
		super();
		this.factureRepository = factureRepository;
		this.patientService = patientService;
		this.seanceService = seanceService;
	}

	public void save(Facture facture) {
		factureRepository.save(facture);
	}

	public List<Facture> findAll() {
		List<Facture> factures = factureRepository.findAll();
		//Fetch the patient in seance and the adress in Patient
		factures.forEach(n -> {
					patientService.fetchLazy(n.getPatient());
					n.getSeances().forEach(x -> seanceService.fetchLazyAtributes(x));	
					
		});
		return factures;
	}

}
