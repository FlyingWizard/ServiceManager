package dao.service;

import java.util.List;

import dao.entity.Address;
import dao.entity.Contact;
import dao.entity.ContactType;

public class ContactDao extends DaoService {
	public void insertContact(Contact c) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(c);
			tx.commit();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

	}

	/**
	 * Selects all Addresss.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> findAllContacts() throws Exception {
		List<Contact> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllContacts").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

		return result;
	}
	
	public void insertContactType(ContactType ct) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(ct);
			tx.commit();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

	}
	/**
	 * Find an contact with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Contact findContactByKey(int key) throws Exception{
		Contact result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(Contact.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public ContactType findContactType(String type) throws Exception{
		List<ContactType> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findContacttypeByType")
					.setParameter("itype", type.toLowerCase())
				    .getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}
		if(result != null && !result.isEmpty()){
			return result.get(0);
		}
		ContactType ct = new ContactType(type);
		this.insertContactType(ct);
		return ct;
	}
	@SuppressWarnings("unchecked")
	public ContactType findAllContactTypes() throws Exception{
		List<ContactType> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllContacttypes").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}
		if(result != null && !result.isEmpty()){
			return result.get(0);
		}
		return null;
	}
	/**
	 * Find an conttttacttype with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ContactType findContactTypeByKey(int key) throws Exception{
		ContactType result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(ContactType.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}
}
