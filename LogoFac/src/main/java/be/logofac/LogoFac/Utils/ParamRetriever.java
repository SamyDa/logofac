package be.logofac.LogoFac.Utils;

import java.util.List;
import java.util.stream.Collectors;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.AppParameter;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.service.ParameterService;

public class ParamRetriever {

	ParameterService parameterService;
	
	public ParamRetriever() {
		parameterService = FrontApp.serviceCatalog.getParameterService();
	}

	public AppParameter retrieveParameter(AppParameter appParameter) {
		
		if(appParameter instanceof AppParameterAmount) {
			return retrieveAmount((AppParameterAmount)appParameter);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private AppParameterAmount retrieveAmount(AppParameterAmount appParameter) {
		
		return ((List<AppParameterAmount>)  parameterService.findAllAmounts().stream().filter(n -> n.getDuration() == appParameter.getDuration() && n.getSeanceType() == appParameter.getSeanceType()).collect(Collectors.toList())).get(0);
		
	}
	
}
