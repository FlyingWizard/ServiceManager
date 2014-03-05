package dao;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import dao.entity.Address;
import dao.entity.Customer;
import dao.entity.Employee;
import dao.service.ContactDao;
import dao.service.CustomerDao;
import dao.service.EmployeeDao;


public class EmployeeDaoTest {
	
	@Before
	public void initEmfAndEm() {
		Logger.getLogger("org").setLevel(Level.SEVERE);
	}

	@Test
	public void testEmployees() {
		EmployeeDao ed = new EmployeeDao();
		Employee test = null;
		int key = 0;
		try {
			Address a = new Address("Veldweg","103","2260","Westerlo");
			Employee emp = new Employee("Jos", "HeylenTest", a, "jh@mail.com", "0488556699");
			ed.insertEmployee(emp);
			key = emp.getId();
			test = ed.findEmployeeByKey(key);
			assertTrue(test!=null);
			assertTrue(test.getHomeAddress()!=null && test.getHomeAddress().getId()>0);
			
			List<Employee> employees = ed.findAllEmployees();			
			assertTrue(employees!=null && employees.size()>0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	@Test
	public void testDeleteEmployees() {
		
		EmployeeDao ed = new EmployeeDao();
		Employee test = null;
		int key = 0;

		try {
			List<Employee> employees = ed.findAllEmployees();
			for(Employee e : employees){
				if(e.getLastName().equals("HeylenTest")){
					test = e;
				}
			}
			key = test.getId();
			ed.deleteEmployee(test);
			test = null;
			test = ed.findEmployeeByKey(key);
			assertTrue(test == null);
			for(Employee e : ed.findAllEmployees()){
				if(e.getLastName().equals("HeylenTest")){
					ed.deleteEmployee(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
