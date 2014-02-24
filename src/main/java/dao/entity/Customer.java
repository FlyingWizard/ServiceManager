package dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String fName;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String lName;
	@Column(nullable = false, columnDefinition="varchar(20)")
	private String vatNr;
	@ManyToMany
	private List<Address> deliveryAdresses;
	@OneToOne
	private Address invoiceAddresses;
	
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
		return deliveryAdresses;
	}
	public void setDeliveryAdresses(List<Address> deliveryAdresses) {
		this.deliveryAdresses = deliveryAdresses;
	}
	public Address getInvoiceAddresses() {
		return invoiceAddresses;
	}
	public void setInvoiceAddresses(Address invoiceAddresses) {
		this.invoiceAddresses = invoiceAddresses;
	}
	@Override
	public String toString(){
		return "Customer " + id;
	}
}
