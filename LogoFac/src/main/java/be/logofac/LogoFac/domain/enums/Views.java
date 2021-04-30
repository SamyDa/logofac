package be.logofac.LogoFac.domain.enums;

import be.logofac.LogoFac.views.ManageParametersPane;
import be.logofac.LogoFac.views.ManagePatientDataPane;
import be.logofac.LogoFac.views.ManageProDataPane;
import be.logofac.LogoFac.views.PatientDataDialogPane;

public enum Views {

	OptionView_ManagePatientData( (ManagePatientDataPane.class),"Option manage patient data") ,
	OptionView_ManageProData( ManageProDataPane.class,"Option manage professionnal data"), 
	OptionView_ManageParameter(ManageParametersPane.class, "Option manage parameters"),
	ManagePatientData_Dialog(PatientDataDialogPane.class , "Dialog with patient data") ;
	
	
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
