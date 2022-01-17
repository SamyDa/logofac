package be.logofac.LogoFac.Utils;


import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;

public class CacheData {

	
	private Patient patient;
	
	private Patient selectedPatient;

	private Facture selectedInvoice;
	
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

	public Facture getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(Facture selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}
	
	
	
}
