package be.logofac.LogoFac.domain;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seanceId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_patient")
	private Patient patient;

	private LocalDateTime hourFrom;
	
	private double hourNumber;

	
	public Seance() {
		super();
	}



	public Seance( Patient patient, LocalDateTime hourFrom, double hourNumber) {
		super();
		this.patient = patient;
		this.hourFrom = hourFrom;
		this.hourNumber = hourNumber;
	}



	@Override
	public String toString() {
		return "Seance [seanceId=" + seanceId + ", patient=" + patient.toString() + ", hourFrom=" + hourFrom + ", hourNumber="
				+ hourNumber + "]";
	}



	public int getSeanceId() {
		return seanceId;
	}



	public void setSeanceId(int seanceId) {
		this.seanceId = seanceId;
	}



	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public LocalDateTime getHourFrom() {
		return hourFrom;
	}



	public void setHourFrom(LocalDateTime hourFrom) {
		this.hourFrom = hourFrom;
	}



	public double getHourNumber() {
		return hourNumber;
	}



	public void setHourNumber(double hourNumber) {
		this.hourNumber = hourNumber;
	} 
	
	
	
	
}
