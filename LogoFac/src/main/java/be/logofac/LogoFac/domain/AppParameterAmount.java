package be.logofac.LogoFac.domain;

import javax.persistence.Entity;

import be.logofac.LogoFac.domain.enums.ParameterType;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;

@Entity
public class AppParameterAmount extends AppParameter {

	
	private double amount;
	private String description;
	private SeanceType seanceType;
	private SeanceDuration duration;
	
	
	public AppParameterAmount(double amount, String description, SeanceType seanceType, SeanceDuration duration) {
		super();
		this.paramType = ParameterType.Amount;
		this.amount = amount;
		this.description = description;
		this.seanceType = seanceType;
		this.duration = duration;
	}
	
	public AppParameterAmount() {
		super();
		this.paramType = ParameterType.Amount;
	}
	public AppParameterAmount(SeanceDuration duration, SeanceType seanceType) {
		this.paramType = ParameterType.Amount;
		this.amount = 0;
		this.description = "";
		this.seanceType = seanceType;
		this.duration = duration;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SeanceType getSeanceType() {
		return seanceType;
	}
	public void setSeanceType(SeanceType seanceType) {
		this.seanceType = seanceType;
	}
	public SeanceDuration getDuration() {
		return duration;
	}
	public void setDuration(SeanceDuration duration) {
		this.duration = duration;
	}
	
	
	
}
