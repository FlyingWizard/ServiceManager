package dao.service;

import java.util.ArrayList;
import java.util.List;

import dao.entity.Employee;

public class EmployeeDao extends DaoService {
	public void insertEmployee(Employee emp) throws Exception {
		this.saveObject(emp);
	}

	/**
	 * Selects all Employees.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Employee> findAllEmployees() throws Exception {
		List<Employee> result = new ArrayList<Employee>();
		List<Object> temp = this.getResultsList("findAllEmployees");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((Employee)o);
			}
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
		Employee result = getEntityManager().find(Employee.class, key);
		return result;
	}
	public void deleteEmployee(Employee empl) throws Exception{
		this.removeObject(empl);
	}

}

