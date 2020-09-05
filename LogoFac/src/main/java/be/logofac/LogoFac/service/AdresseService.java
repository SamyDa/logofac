package be.logofac.LogoFac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.repository.AdresseRepository;

@Service
public class AdresseService {
	
	private AdresseRepository adresseRepository;

	public AdresseService(AdresseRepository adresseRepository) {
		super();
		this.adresseRepository = adresseRepository;
	}

	public List<Adresse> findAllAdresses() {
		return adresseRepository.findAll();
		
	}

	public void save(Adresse adresse) {
		adresseRepository.save(adresse);
		
	}


}
