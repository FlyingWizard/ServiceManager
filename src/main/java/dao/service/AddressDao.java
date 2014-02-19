package dao.service;

import java.util.List;

import javax.persistence.NoResultException;

import dao.entity.Address;

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
	public List<Address> findAllAddresss() throws Exception {
		List<Address> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllAddresses").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

		return result;
	}

}
