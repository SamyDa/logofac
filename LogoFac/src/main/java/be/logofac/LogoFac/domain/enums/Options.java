package be.logofac.LogoFac.domain.enums;

public enum Options {
	AmountManagement("Gestion des montants"),
	PatientManagement("Gestion des patients"), 
	ProManagement("Gestion des données professionnelles"), 
	AppGenParameters("Gestion des paramètres généraux");
	
	private String description;

	private Options(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
	
}
