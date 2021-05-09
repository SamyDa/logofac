package be.logofac.LogoFac.domain.enums;

public enum Mois {
	
	janvier(1), février(2), mars(3), avril(4), mai(5), juin(6), juillet(7), août(8), septembre(9), octobre(10), novembre(11), décembre(12);
	
	private int numMois = 0;
	
	Mois(int numMois) {
		this.numMois = numMois;
	}

	
	public boolean checkMois(int numMois) {
		return this.numMois == numMois;
	}
	
	public int getMois() {
		return numMois;
	}
}
