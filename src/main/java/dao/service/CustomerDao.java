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
			throw e;
		} finally {
			this.cleanResources();
		}

	}

	/**
	 * Selects all Customer.
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
			throw e;
		} finally {
			this.cleanResources();
		}

		return result;
	}
	/**
	 * Find an Customer with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Customer findCustomerByKey(int key) throws Exception{
		Customer result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(Customer.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}
	/**
	 * Find customers with a specified last name.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findCustomersByLastName(String name) throws Exception{
		List<Customer> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findCustomersByLastName")
					.setParameter("iLname", name.toLowerCase())
				    .getResultList();

		} catch (Exception e) {
			throw e;
		} finally {
			this.cleanResources();
		}
		
		return result;
	}
	public void deleteCustomer(Customer c) throws Exception{
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.remove(em.merge(c));
			tx.commit();

		} catch (Exception e) {
			throw e;
		} finally {
			this.cleanResources();
		}
	}
}
