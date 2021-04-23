package be.logofac.LogoFac.domain.enums;

public enum Options {
	
	PatientManagement("Gestion des patients"), ProManagement("Gestion des données professionnelles");
	
	private String description;

	private Options(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
	
}
