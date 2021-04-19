package be.logofac.LogoFac.domain;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int factureId;
	//@Column(unique =  true)
	private String reference;
	
	private boolean printed = false;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Professionnel professionnel;
	
	private String communication;
	
	private LocalDate applicationDate;
	
	private LocalDate creationDate;
	
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable( name = "jnd_FactSeance" , joinColumns = @JoinColumn(name = "facture_fk") ,inverseJoinColumns = @JoinColumn( name = "seance_fk") )
	private List<Seance> seances;
	
	public Facture() {
		super();
	}

	public Facture(String reference, boolean printed, Patient patient, Professionnel professionnel,
			String communication, LocalDate applicationDate, LocalDate creationDate, List<Seance> seances) {
		super();
		this.reference = reference;
		this.printed = printed;
		this.patient = patient;
		this.professionnel = professionnel;
		this.communication = communication;
		this.applicationDate = applicationDate;
		this.creationDate = creationDate;
		this.seances = seances;
	}

	@Override
	public String toString() {
		return "Facture [factureId=" + factureId + ", reference=" + reference + ", patient=" + patient.toString()
				+ ", communication=" + communication + ", seances=" + seances.toString() + "]";
	}

	public int getFactureId() {
		return factureId;
	}

	public void setFactureId(int factureId) {
		this.factureId = factureId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}


	public Professionnel getProfessionnel() {
		return professionnel;
	}


	public void setProfessionnel(Professionnel professionnel) {
		this.professionnel = professionnel;
	}


	public boolean isPrinted() {
		return printed;
	}

	

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	

	public static String createReference(int year, int invoiceCount) {
		// TODO Auto-generated method stub
		return year + "-" + invoiceCount;
	}
	
	
}
