package be.logofac.LogoFac.domain;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int patientId;
	
	private String firstName ; 
	private String lastName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn( name =  "fk_adresse")
	private Adresse adresse;
	
	

	public Patient() {
		super();
	}

	public Patient ( String firstName, String lastName, Adresse adresse) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
	}

	
	
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", adresse="
				+ adresse.toString() + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getPatientId() {
		return this.patientId;
	}
	
	
}
