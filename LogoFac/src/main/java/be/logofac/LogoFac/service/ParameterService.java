package be.logofac.LogoFac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.AppParameter;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.domain.AppParameterGeneral;
import be.logofac.LogoFac.domain.enums.ParameterReference;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;
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
	public List<AppParameterGeneral> findAllGeneral(){
		
		return parameterRepository.findAllGeneral();
		
	}

	public AppParameterGeneral findGeneralParameter(ParameterReference folderLocation) {
		return parameterRepository.findByParameterReference(folderLocation);
	}
	
	
	public void addMissingAmountParameter()
	{
		List<AppParameterAmount> amountList = findAllAmounts();
		for(SeanceType seanceType : SeanceType.values()) {
			for(SeanceDuration seanceDuration : SeanceDuration.values()) {				
				if(amountList.stream().filter(amount -> amount.getDuration().equals(seanceDuration) && amount.getSeanceType().equals(seanceType)).collect(Collectors.toList()).size()==0) {
					AppParameterAmount newAppAmount = new AppParameterAmount();
					newAppAmount.setAmount(0);
					newAppAmount.setSeanceType(seanceType);
					newAppAmount.setDuration(seanceDuration);
					newAppAmount.setDescription("Automatically created (was missing)");
					parameterRepository.save(newAppAmount);
				}
			}			
		}
	}
}
