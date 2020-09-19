package be.logofac.LogoFac.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.repository.ProfessionnelRepository;

@Service
public class ProfessionnelService {

	private ProfessionnelRepository professionnelRepository;
	private AdresseService adresseService;


	public ProfessionnelService(ProfessionnelRepository professionnelRepository, AdresseService adresseService) {
		super();
		this.professionnelRepository = professionnelRepository;
		this.adresseService = adresseService;
	}

	public void save(Professionnel pro) {
		adresseService.save(pro.getAdresse());
		professionnelRepository.save(pro);
		
	}

	public List<Professionnel> findAllPro() {
		return professionnelRepository.findAllAndAdress();
	}

	public void fetchLazy(Professionnel professionnel) {
		Professionnel fetchedPro  = professionnelRepository.fetchByid(professionnel.getProfessionnelId());
		professionnel.setAdresse(fetchedPro.getAdresse());
		
	}
	
	
}
