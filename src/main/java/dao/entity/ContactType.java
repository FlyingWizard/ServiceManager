package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "findAllContacttypes", query = "SELECT ct FROM ContactType ct"),
@NamedQuery(name = "findContacttypeByType", query = "SELECT ct FROM ContactType ct where ct.type like :itype")
})
public class ContactType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String type;
	
	public ContactType(){}
	public ContactType(String type){
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString(){
		return "type "+id+" - "+type;
	}
}