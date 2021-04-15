package be.logofac.LogoFac.domain.enums;

public enum SeanceType {
	Cabinet("au cabinet"), Domicile("à domicile"), Online("en ligne");

	private String textType;
	
	SeanceType(String textType) {
		this.textType = textType;
	} 
	
	
	public String getSeanceString() {
		return textType;
	}
	
	
	
}
