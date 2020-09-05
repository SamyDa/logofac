package be.logofac.LogoFac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Prix;
import be.logofac.LogoFac.repository.PrixRepository;

@Service
public class PrixService {
	
	private PrixRepository prixRepository;

	public PrixService(PrixRepository prixRepository) {
		super();
		this.prixRepository = prixRepository;
	}

	public void save(Prix price) {
		prixRepository.save(price);
		
	}

	public List<Prix> getAllPrices() {
		return prixRepository.findAll();
	}
	
	

}
