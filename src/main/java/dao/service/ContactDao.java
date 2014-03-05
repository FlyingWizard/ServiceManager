package dao.service;

import java.util.ArrayList;
import java.util.List;

import dao.entity.Contact;
import dao.entity.ContactType;

public class ContactDao extends DaoService {
	public void insertContact(Contact c) throws Exception{
		this.saveObject(c);
	}
	public void insertContactType(ContactType c) throws Exception{
		this.saveObject(c);
	}
	/**
	 * Selects all Addresss.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Contact> findAllContacts() throws Exception {
		List<Contact> result = new ArrayList<Contact>();

		List<Object> temp = this.getResultsList("findAllContacts");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((Contact)o);
			}
		}

		return result;
	}
	
	/**
	 * Find an contact with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Contact findContactByKey(int key) throws Exception{
		Contact result = getEntityManager().find(Contact.class, key);
		return result;
	}
	/**
	 * Search a specific contactType.
	 * When not found the type will be created.
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ContactType findContactType(String type) throws Exception{
		List<ContactType> result = new ArrayList<ContactType>();

		List<Object> temp = getEntityManager().createNamedQuery("findContacttypeByType")
				.setParameter("itype", type.toLowerCase()).getResultList();
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((ContactType)o);
			}
		}

		if(result != null && !result.isEmpty()){
			return result.get(0);
		}
		ContactType ct = new ContactType(type);
		this.saveObject(ct);
		return ct;
	}
	public List<ContactType> findAllContactTypes() throws Exception{
		List<ContactType> result = new ArrayList<ContactType>();
		List<Object> temp = this.getResultsList("findAllContacttypes");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((ContactType)o);
			}
		}
		return result;
	}
	/**
	 * Find an conttttacttype with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ContactType findContactTypeByKey(int key) throws Exception{
		ContactType result = getEntityManager().find(ContactType.class, key);
		return result;
	}
	public void deleteContact(Contact c) throws Exception{
		this.removeObject(c);
	}
}
