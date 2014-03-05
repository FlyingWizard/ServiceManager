package dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findAllServiceCalls", query = "SELECT s FROM ServiceCall s")
public class ServiceCall {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Customer customer;
	@Column(nullable = false, columnDefinition="varchar(100)")
	private String shortDescription;
	@Column(nullable = false)
	private String longDescription;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date opened;
	@Temporal(TemporalType.TIMESTAMP)
	private Date closed;
	@ManyToOne
	private Employee responsible;
	
	public ServiceCall(){}
	/**
	 * Creates a new service calls.
	 * Auto sets the opened data.
	 * @param cust
	 * @param sd
	 * @param ld
	 * @param resp
	 */
	public ServiceCall(Customer cust, String sd, String ld, Employee resp){
		this.shortDescription = sd;
		this.longDescription = ld;
		this.opened = new Date();
		this.addCustomer(cust);
		this.addResponsible(resp);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public Date getOpened() {
		return opened;
	}
	public void setOpened(Date opened) {
		this.opened = opened;
	}
	public Date getClosed() {
		return closed;
	}
	public void setClosed(Date closed) {
		this.closed = closed;
	}
	public Employee getResponsible() {
		return responsible;
	}
	/**
	 * User AddResponsible instead of this set so the bi-directional relationship will be set properly
	 * @param responsible
	 */
	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}
	
	/**
	 * Set both ends of bi-directional connection
	 * @param c
	 */
	public void addCustomer(Customer c){
		c.addServiceCall(this);
	}
	/**
	 * Set both ends of bi-directional connection
	 * @param e
	 */
	public void addResponsible(Employee e){
		e.addServiceCall(this);
	}
	@Override
	public String toString(){
		return "ServiceCall " + id;
	}
}
