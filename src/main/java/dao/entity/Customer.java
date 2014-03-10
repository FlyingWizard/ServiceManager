package dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import dao.service.ContactTypeCST;

@Entity
@Inheritance
@DiscriminatorValue("Customer")
@NamedQueries({
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c"),
@NamedQuery(name = "findCustomersByLastName", query = "SELECT c FROM Customer c where LOWER(c.lastName) like :iLname")
})
public class Customer extends Person{
	private String vatNr;
	@ManyToMany(cascade=javax.persistence.CascadeType.ALL)
	private List<Address> deliveryAddresses;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	private Address invoiceAddress;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy="customer")
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
	 * Creates customer with address for home, invoicing and delivery
	 * @param fn
	 * @param ln
	 * @param vatnr
	 * @param a
	 */
	public Customer(String fn, String ln, String vatnr,Address a){
		this(fn,ln,vatnr);
		this.invoiceAddress = a;
		this.homeAddress = 	a;
		this.deliveryAddresses = new ArrayList<Address>();
		this.deliveryAddresses.add(a);		
	}
	public Customer(String fn, String ln, String vatnr){
		this.firstName = fn;
		this.lastName = ln;
		this.vatNr = vatnr;	
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
