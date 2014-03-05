package dao.service;

import java.util.ArrayList;
import java.util.List;

import dao.entity.Customer;

public class CustomerDao extends DaoService {
	public void insertCustomer(Customer cust) throws Exception {
		this.saveObject(cust);
	}

	/**
	 * Selects all Customer.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Customer> findAllCustomers() throws Exception {
		List<Customer> result = new ArrayList<Customer>();

		List<Object> temp = this.getResultsList("findAllCustomers");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((Customer)o);
			}
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
		Customer result = getEntityManager().find(Customer.class, key);
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
		List<Customer> result = new ArrayList<Customer>();
		List<Object> temp = getEntityManager().createNamedQuery("findCustomersByLastName")
				.setParameter("iLname",name.toLowerCase()).getResultList();
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((Customer)o);
			}
		}
		return result;
	}
	public void deleteCustomer(Customer c) throws Exception{
		this.removeObject(c);
	}
}
