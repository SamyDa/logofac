package be.logofac.LogoFac.domain.enums;

public enum Mois {
	
	Janvier(1), Février(2), Mars(3), Avril(4), Mai(5), Juin(6), Juillet(7), Août(8), Septembre(9), Octobre(10), Novembre(11), Décembre(12);
	
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
