package be.logofac.LogoFac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.repository.SeanceRepository;

@Service
public class SeanceService {

	private SeanceRepository seanceRepository;

	public SeanceService(SeanceRepository seanceRepository) {
		super();
		this.seanceRepository = seanceRepository;
	}

	public void save(Seance seance) {
		seanceRepository.save(seance);		
	}
	
	public List<Seance> findAllSeance(){
		return seanceRepository.findAllAndPatient();
		
	}
	public void fetchLazyAtributes(Seance seance) {
		//first fetch the patient then fetch the address of the Patient
		Seance fetchedSeance = seanceRepository.fetchById(seance.getSeanceId());
		seance.setPatient(fetchedSeance.getPatient());
		seance.setProfessionnel(fetchedSeance.getProfessionnel());
	}

}
	

