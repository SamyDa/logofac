package be.logofac.LogoFac.domain.enums;


import java.util.List;
import java.util.stream.Collectors;

public enum SeanceType {
	Cabinet("au cabinet",true), Domicile("à domicile",true), Online("en ligne",true),
	Bilan_Cabinet("de bilan au cabinet",true), Bilan_Domicile("de bilan à domicile",true),
	CabinetBim("au cabinet",false), DomicileBim("à domicile",false) , OnlineBim("en ligne", false),Bilan_Cabinet_Bim("de bilan au cabinet",false), 
	Bilan_Domicile_Bim("de bilan à domicile",false)
	;

	private String textType;
	private boolean toShow; 
	
	SeanceType(String textType , boolean toShow) {
		this.textType = textType;
		this.toShow = toShow;
	} 
	
	
	public String getSeanceString() {
		return textType;
	}

	public boolean isToShow() {
		return toShow;
	}
	
	public SeanceType getBimEquivalent() {
		List<SeanceType> bimSeanceList = List.of(CabinetBim, DomicileBim , OnlineBim,Bilan_Cabinet_Bim,Bilan_Domicile_Bim);
		List<SeanceType> validBimTypeList = bimSeanceList.stream().filter(seance -> seance.getSeanceString().equals(this.getSeanceString())).collect(Collectors.toList());
				
		return validBimTypeList.size() > 0 ? validBimTypeList.get(0) : this; 
	}
}
