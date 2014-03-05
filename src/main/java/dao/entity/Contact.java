package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllContacts", query = "SELECT c FROM Contact c")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String contact;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	private ContactType type;
	
	public Contact(){}
	public Contact(String contact, ContactType type){
		this.contact =  contact;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public ContactType getType() {
		return type;
	}
	public void setType(ContactType type) {
		this.type = type;
	}
	@Override
	public String toString(){
		return "Contact "+id+" - " + type + ":"+ contact;
	}
}
