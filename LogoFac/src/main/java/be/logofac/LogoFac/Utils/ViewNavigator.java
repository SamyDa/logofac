package be.logofac.LogoFac.Utils;

import be.logofac.LogoFac.domain.enums.Options;
import be.logofac.LogoFac.domain.enums.Views;
import be.logofac.LogoFac.views.NavigationPane;

public class ViewNavigator {
	
	
	public NavigationPane getViewFromInputOption(Options option) {
		
		if(option.equals(Options.PatientManagement)) {
			return retrievePaneFromView(Views.OptionView_ManagePatientData);
			
		}
		
		if(option.equals(Options.ProManagement)) {
			return retrievePaneFromView(Views.OptionView_ManageProData); 
		}
		
		return null;
	}
	
	private NavigationPane retrievePaneFromView(Views view) {
		
		
		try {
			NavigationPane navigationPane =(NavigationPane) view.getPane().newInstance();
			return navigationPane;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
