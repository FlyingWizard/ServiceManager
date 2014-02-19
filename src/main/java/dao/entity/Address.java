package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllAddresses", query = "SELECT a FROM Address a")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false, columnDefinition="varchar(20)")
	private String hNumber;
	@Column(nullable = false, columnDefinition="varchar(30)")
	private String zipCode;
	@Column(nullable = false)
	private String city;
	@Column(columnDefinition="varchar(100) default 'BE'")
	private String country;
	@Column(columnDefinition="bit default 1")
	private boolean active;
	@Column(length = 500)
	private String remark;
	
	public Address(String street, String hnr, String zip, String city, String country){
		this.street=street;
		this.hNumber=hnr;
		this.zipCode=zip;
		this.city = city;
		this.country=country;
		this.active=true;
	}
	public Address(String street, String hnr, String zip, String city){
		this.street=street;
		this.hNumber=hnr;
		this.zipCode=zip;
		this.city=city;
		this.active=true;
	}
	/**
	 * Default constructor required for EclipseLink
	 */
	public Address(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String gethNumber() {
		return hNumber;
	}
	public void sethNumber(String hNumber) {
		this.hNumber = hNumber;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		country = country;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
