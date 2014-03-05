package dao.service;

import java.util.List;

import dao.entity.Address;
import dao.entity.Employee;

public class EmployeeDao extends DaoService {
	public void insertEmployee(Employee emp) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(emp);
			tx.commit();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

	}

	/**
	 * Selects all Employees.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() throws Exception {
		List<Employee> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllEmployees").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

		return result;
	}
	/**
	 * Find an Employee with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Employee findEmployeeByKey(int key) throws Exception{
		Employee result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(Employee.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}

}

