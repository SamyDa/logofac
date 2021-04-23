package be.logofac.LogoFac.Utils;


import be.logofac.LogoFac.domain.Patient;

public class CacheData {

	
	private Patient patient;
	
	private Patient selectedPatient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}
	
	
}
