package be.logofac.LogoFac.domain;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int factureId;
	@Column(unique =  true)
	private String reference;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	private String communication;
	
	@OneToMany(fetch = FetchType.EAGER )
	@JoinTable( name = "jnd_FactSeance" , joinColumns = @JoinColumn(name = "facture_fk") ,inverseJoinColumns = @JoinColumn( name = "seance_fk") )
	private List<Seance> seances;

	
	
	public Facture() {
		super();
	}

	public Facture(String reference, Patient patient, String communication, List<Seance> seances) {
		super();
		this.reference = reference;
		this.patient = patient;
		this.communication = communication;
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
	
	
}
