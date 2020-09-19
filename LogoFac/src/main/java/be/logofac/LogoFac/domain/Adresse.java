package be.logofac.LogoFac.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adresseId;
	
	private String street;
	private String city;
	private String houseNumber;
	private int postalCode;
	private String country;
	
	public Adresse() {
		super();
	}
	
	public Adresse( String street, String city, String houseNumber, int postalCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.country = country;
	}
	public int getAdresseId() {
		return adresseId;
	}
	public void setAdresseId(int adresseId) {
		this.adresseId = adresseId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Adresse [adresseId=" + adresseId + ", street=" + street + ", city=" + city + ", houseNumber="
				+ houseNumber + ", postalCode=" + postalCode + ", country=" + country + "]";
	}
	
	

}
