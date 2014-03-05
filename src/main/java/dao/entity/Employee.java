package dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import dao.service.ContactTypeCST;

@Entity
@NamedQuery(name = "findAllEmployees", query = "SELECT e FROM Employee e")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String firstName;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String lastName;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	private Address homeAddress;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL)
	private List<Contact> contactDetails;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL, mappedBy="responsible")
	private List<ServiceCall> serviceCalls;
	
	public Employee(){}
	public Employee(String fname, String lname, Address home, String email, String mobile){
		this.firstName = fname;
		this.lastName = lname;
		this.homeAddress = home;
		this.contactDetails = new ArrayList<Contact>();
		ContactTypeCST helper = new ContactTypeCST();
		if(email != null)
			this.contactDetails.add(new Contact(email,helper.EMAIL()));
		if(mobile != null)
			this.contactDetails.add(new Contact(mobile,helper.MOBILE()));
	}
	
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
	public List<ServiceCall> getServiceCalls() {
		return serviceCalls;
	}
	public void setServiceCalls(List<ServiceCall> serviceCalls) {
		this.serviceCalls = serviceCalls;
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
	/**
	 * Add a service call 
	 * @param call
	 */
	public void addServiceCall(ServiceCall call){
		if(this.serviceCalls == null){
			this.serviceCalls = new ArrayList<ServiceCall>();
		}
		this.serviceCalls.add(call);
		//Set both ends of bi-directional relation
		call.setResponsible(this);
	}
	@Override
	public String toString(){
		return "Employee " + id;
	}
	
}
