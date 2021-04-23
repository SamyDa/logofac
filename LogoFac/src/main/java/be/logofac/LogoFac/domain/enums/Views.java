package be.logofac.LogoFac.domain.enums;

import be.logofac.LogoFac.views.ManagePatientDataPane;
import be.logofac.LogoFac.views.ManageProDataPane;

public enum Views {

	OptionView_ManagePatientData( (ManagePatientDataPane.class),"Option manage patient data") , OptionView_ManageProData( ManageProDataPane.class,"Option manage professionnal data") ;
	
	
	private Class pane;
	private String viewURL;

	private Views(Class pane,String viewURL) {
		this.pane = pane;
		this.viewURL = viewURL;
	}

	public String getViewURL() {
		return viewURL;
	}

	public Class getPane() {
		return pane;
	}
	
}