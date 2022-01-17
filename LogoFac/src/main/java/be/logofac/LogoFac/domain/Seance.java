package be.logofac.LogoFac.domain;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;

@Entity
public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seanceId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_patient")
	private Patient patient;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_professionnel")
	private Professionnel professionnel;

	private LocalDateTime hourFrom;
	
	private SeanceDuration hourNumber;
	private SeanceType seanceType;
	
	private Boolean toPrint;
	
	private Boolean isThirdPartyPayment; 
	private Boolean isCancelled;

	
	public Seance() {
		super();
	}

	public Seance(Patient patient, Professionnel professionnel, LocalDateTime hourFrom, SeanceDuration hourNumber,
			SeanceType seanceType, Boolean isThirdPartyPayment, Boolean isCancelled) {
		super();
		this.patient = patient;
		this.professionnel = professionnel;
		this.hourFrom = hourFrom;
		this.hourNumber = hourNumber;
		this.seanceType = seanceType;
		this.isThirdPartyPayment = isThirdPartyPayment;
		this.isCancelled = isCancelled;
	}

	@Override
	public String toString() {
		return "Seance [seanceId=" + seanceId + ", patient=" + patient + ", professionnel=" + professionnel
				+ ", hourFrom=" + hourFrom + ", hourNumber=" + hourNumber + "]";
		
	}

	public Boolean getIsThirdPartyPayment() {
		return isThirdPartyPayment;
	}


	public void setIsThirdPartyPayment(Boolean isThirdPartyPayment) {
		this.isThirdPartyPayment = isThirdPartyPayment;
	}


	public Boolean getIsCancelled() {
		return isCancelled;
	}


	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
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


	public SeanceDuration getHourNumber() {
		return hourNumber;
	}


	public void setHourNumber(SeanceDuration hourNumber) {
		this.hourNumber = hourNumber;
	}


	public Professionnel getProfessionnel() {
		return professionnel;
	}


	public void setProfessionnel(Professionnel professionnel) {
		this.professionnel = professionnel;
	}

	public SeanceType getSeanceType() {
		return seanceType;
	}

	public void setSeanceType(SeanceType seanceType) {
		this.seanceType = seanceType;
	}


	public Boolean isToPrint() {
		
		return toPrint;
	}


	public void setToPrint(boolean seanceInvoiced) {
		toPrint = seanceInvoiced;
		
	} 
	
	
}
