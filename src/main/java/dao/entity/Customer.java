package dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import dao.service.ContactTypeCST;

@Entity
@NamedQueries({
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c"),
@NamedQuery(name = "findCustomersByLastName", query = "SELECT c FROM Customer c where LOWER(c.lName) like :iLname")
})
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String fName;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String lName;
	@Column(nullable = true, columnDefinition="varchar(20)")
	private String vatNr;
	@ManyToMany
	private List<Address> deliveryAddresses;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	private Address invoiceAddress;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL)
	private List<Contact> contactDetails;
	@OneToMany(mappedBy="customer")
	private List<ServiceCall> serviceCalls;
	
	public Customer(){}
	
	/**
	 * Creates a full new customer
	 * @param fn
	 * @param ln
	 * @param vatnr
	 * @param street
	 * @param hnr
	 * @param zip
	 * @param city
	 * @param country
	 * @param email
	 * @param mobile
	 * @param fixPhone
	 */
	public Customer(String fn, String ln, String vatnr, String street, String hnr, String zip, String city, String country, String email, String mobile, String fixPhone){
		this(fn,ln,vatnr,new Address(street, hnr, zip, city, country));		
		this.contactDetails = new ArrayList<Contact>();
		ContactTypeCST helper = new ContactTypeCST();
		if(email != null)
			this.contactDetails.add(new Contact(email,helper.EMAIL()));
		if(mobile != null)
			this.contactDetails.add(new Contact(mobile,helper.MOBILE()));
		if(fixPhone != null)
			this.contactDetails.add(new Contact(fixPhone,helper.FIXPHONE()));
	}
	/**
	 * Creates customer with address for invoicing and delivery
	 * @param fn
	 * @param ln
	 * @param vatnr
	 * @param street
	 * @param hnr
	 * @param zip
	 * @param city
	 * @param country
	 */
	public Customer(String fn, String ln, String vatnr, String street, String hnr, String zip, String city, String country){
		this(fn,ln,vatnr,new Address(street, hnr, zip, city, country));		
	}
	/**
	 * Creates customer with address for invoicing and delivery
	 * @param fn
	 * @param ln
	 * @param vatnr
	 * @param a
	 */
	public Customer(String fn, String ln, String vatnr,Address a){
		this(fn,ln,vatnr);
		this.invoiceAddress = a;
		this.deliveryAddresses = new ArrayList<Address>();
		this.deliveryAddresses.add(a);		
	}
	public Customer(String fn, String ln, String vatnr){
		this.fName = fn;
		this.lName = ln;
		this.vatNr = vatnr;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getVatNr() {
		return vatNr;
	}
	public void setVatNr(String vatNr) {
		this.vatNr = vatNr;
	}
	public List<Address> getDeliveryAdresses() {
		return deliveryAddresses;
	}
	public void setDeliveryAdres(List<Address> deliveryAdresses) {
		this.deliveryAddresses = deliveryAdresses;
	}
	
	public Address getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
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
	 * Add and address to the delivery addresses
	 * @param a
	 */
	public void addDeliveryAdres(Address a){
		if(this.deliveryAddresses == null)
			this.deliveryAddresses = new ArrayList<Address>();
		this.deliveryAddresses.add(a);
	}
	/**
	 * Adds a new contact.
	 * Contacttype values can be found in constant.ContactTypeCST
	 * @param value
	 * @param type
	 */
	public void AddContact(String value, ContactType type ){
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
		call.setCustomer(this);
	}
	@Override
	public String toString(){
		return "Customer " + id;
	}
}
