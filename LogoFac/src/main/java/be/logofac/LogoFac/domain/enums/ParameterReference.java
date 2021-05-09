package be.logofac.LogoFac.domain.enums;

public enum ParameterReference {
	
	FOLDER_LOCATION("Destination fichiers pdf" , String.valueOf("")),
	INVOICE_OFFSET("Décalage référnce facture" , Integer.valueOf(0) ),
	TITLE_EMAIL("Titre de l'email", String.valueOf("")),
	BODY_EMAIL("Corps de l'email", String.valueOf(""));

	String name; 
	Object instance;
	
	ParameterReference(String name, Object instance) {
		this.instance = instance;
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public Object getInstance() {
		return instance;
	}
}
