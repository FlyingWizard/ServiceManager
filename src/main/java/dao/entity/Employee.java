package dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import dao.service.ContactTypeCST;

@Entity
@Inheritance
@DiscriminatorValue("Employee")
@NamedQuery(name = "findAllEmployees", query = "SELECT e FROM Employee e")
public class Employee extends Person{
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
	
	
	public List<ServiceCall> getServiceCalls() {
		return serviceCalls;
	}
	public void setServiceCalls(List<ServiceCall> serviceCalls) {
		this.serviceCalls = serviceCalls;
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
