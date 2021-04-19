package be.logofac.LogoFac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.AppParameter;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.repository.ParameterRepository;

@Service
public class ParameterService {

	ParameterRepository parameterRepository ;

	public ParameterService(ParameterRepository parameterRepository ) {
		super();
		this.parameterRepository = parameterRepository;
	}

	public void findParameter(AppParameterAmount appParameter) {
		
		//parameterRepository.findAmount();
		
		
	}

	public void save(AppParameter appParameter) {
		parameterRepository.save(appParameter);
	}

	public List<AppParameter> findAll() {
		return parameterRepository.findAll();
	}
	
	
	public List<AppParameterAmount> findAllAmounts(){
		
		return parameterRepository.findAllAmounts();
		
	}
	
	
	
	
	
}
