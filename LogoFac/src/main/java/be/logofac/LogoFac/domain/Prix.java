package be.logofac.LogoFac.domain;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prix {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prixId;
	
	private LocalDate dateFrom; 
	private LocalDate dateTo;
	private double prix;
	
	
	public Prix( LocalDate dateFrom, LocalDate dateTo, double prix) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.prix = prix;
	}
	
	public Prix() {
		super();
	}
	
	@Override
	public String toString() {
		return "Prix [prixId=" + prixId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", prix=" + prix + "]";
	}
	public int getPrixId() {
		return prixId;
	}
	public void setPrixId(int prixId) {
		this.prixId = prixId;
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	public LocalDate getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	
	
}
