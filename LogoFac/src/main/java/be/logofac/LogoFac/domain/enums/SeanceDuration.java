package be.logofac.LogoFac.domain.enums;

public enum SeanceDuration {

	demi_heure("une demi-heure",0.5), heure("une heure",1);
	
	private String descrption;
	private double duration; 

	SeanceDuration(String descrption, double duration) {
		this.duration = duration;
		this.descrption = descrption;
	}

	public String getDescrption() {
		return descrption;
	}

	public double getDuration() {
		return duration;
	}
	
}
