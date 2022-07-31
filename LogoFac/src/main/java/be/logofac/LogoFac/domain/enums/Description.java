package be.logofac.LogoFac.domain.enums;

public enum Description {
	
	Honoraires("Honoraires");

	private String description;
	
	Description(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDescription( String initialText , Mois mois) {
		String text = initialText +  " du mois ";
		if(mois.name().toCharArray()[0] == 'A' || mois.name().toCharArray()[0] == 'O')
			text = text + "d'" + mois.name();
		else
			text = text + "de " + mois.name(); 
		return text;
	}
}
