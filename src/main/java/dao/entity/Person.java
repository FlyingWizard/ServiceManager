package dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance
@DiscriminatorColumn(name="PRS_TYPE",discriminatorType=DiscriminatorType.STRING, length=20)
public abstract class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	@Column(nullable = false, columnDefinition="varchar(100)")
	protected String firstName;
	@Column(nullable = false, columnDefinition="varchar(100)")
	protected String lastName;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	protected Address homeAddress;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL)
	protected List<Contact> contactDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public List<Contact> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(List<Contact> contactDetails) {
		this.contactDetails = contactDetails;
	}
	/**
	 * Adds a new contact.
	 * Contacttype values can be found in constant.ContactTypeCST
	 * @param value
	 * @param type
	 */
	public void addContact(String value, ContactType type ){
		if(this.contactDetails == null){
			this.contactDetails = new ArrayList<Contact>();
		}
		this.contactDetails.add(new Contact(value,type));
	}
}
