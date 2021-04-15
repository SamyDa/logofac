package be.logofac.LogoFac.domain;

import javax.persistence.Entity;

import be.logofac.LogoFac.domain.enums.AmountType;
import be.logofac.LogoFac.domain.enums.ParameterType;

@Entity
public class AppParameterAmount extends AppParameter {

	
	private double amount;
	private String description;
	private AmountType amountType;
	
	public AppParameterAmount(double amount, String description, AmountType amountType) {
		super();
		this.paramType = ParameterType.Amount;
		this.amount = amount;
		this.description = description;
		this.amountType = amountType;
	}
	public AppParameterAmount() {
		super();
		this.paramType = ParameterType.Amount;
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
	public AmountType getAmountType() {
		return amountType;
	}
	public void setAmountType(AmountType amountType) {
		this.amountType = amountType;
	}
	
	
	
}
