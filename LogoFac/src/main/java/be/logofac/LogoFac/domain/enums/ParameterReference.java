package be.logofac.LogoFac.domain.enums;

public enum ParameterReference {
	
	FOLDER_LOCATION("Destination fichiers pdf" , String.valueOf("")),
	INVOICE_OFFSET("Décalage référnce facture" , Integer.valueOf(0) )
	;

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
