package dao.service;

import java.util.List;

import dao.entity.Address;
import dao.entity.Customer;

public class AddressDao extends DaoService {
	public void insertAddress(Address myAddress) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(myAddress);
			tx.commit();

		} catch (Exception e) {
			throw e;
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
	public List<Address> findAllAddresss() throws Exception {
		List<Address> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllAddresses").getResultList();

		} catch (Exception e) {
			throw e;
		} finally {
			this.cleanResources();
		}

		return result;
	}
	/**
	 * Find an address with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Address findAddressByKey(int key) throws Exception{
		Address result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(Address.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}
	public void deleteAddress(Address a) throws Exception{
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.remove(em.merge(a));
			tx.commit();

		} catch (Exception e) {
			throw e;
		} finally {
			this.cleanResources();
		}
	}

}
