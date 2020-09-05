package be.logofac.LogoFac.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Professionnel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int professionnelId;

	private String firstName;
	private String lastName;
	private String BCENumber;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_adresseId")
	private Adresse adresse;
	private String profession;
	private String phoneNumber;
	private String email;
	private String inamiNumber;
	private String IbanAccount;
	private String BicAccount;
	
	
	public Professionnel() {
		super();
	}
	
	public Professionnel( String firstName, String lastName, String bCENumber, Adresse adresse,
			String profession, String phoneNumber, String email, String inamiNumber, String ibanAccount,
			String bicAccount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		BCENumber = bCENumber;
		this.adresse = adresse;
		this.profession = profession;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.inamiNumber = inamiNumber;
		IbanAccount = ibanAccount;
		BicAccount = bicAccount;
	}
	
	@Override
	public String toString() {
		return "Professionnel [professionnelId=" + professionnelId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", BCENumber=" + BCENumber + ", adresse=" + adresse.toString() + ", profession=" + profession
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", inamiNumber=" + inamiNumber
				+ ", IbanAccount=" + IbanAccount + ", BicAccount=" + BicAccount + "]";
	}
	public int getProfessionnelId() {
		return professionnelId;
	}
	public void setProfessionnelId(int professionnelId) {
		this.professionnelId = professionnelId;
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
	public String getBCENumber() {
		return BCENumber;
	}
	public void setBCENumber(String bCENumber) {
		BCENumber = bCENumber;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInamiNumber() {
		return inamiNumber;
	}
	public void setInamiNumber(String inamiNumber) {
		this.inamiNumber = inamiNumber;
	}
	public String getIbanAccount() {
		return IbanAccount;
	}
	public void setIbanAccount(String ibanAccount) {
		IbanAccount = ibanAccount;
	}
	public String getBicAccount() {
		return BicAccount;
	}
	public void setBicAccount(String bicAccount) {
		BicAccount = bicAccount;
	}
	
	
	
}
