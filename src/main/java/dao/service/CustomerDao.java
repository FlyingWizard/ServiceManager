package dao.service;

import java.util.List;

import dao.entity.Customer;

public class CustomerDao extends DaoService {
	public void insertCustomer(Customer cust) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(cust);
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
	public List<Customer> findAllCustomers() throws Exception {
		List<Customer> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllCustomers").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

		return result;
	}

}
