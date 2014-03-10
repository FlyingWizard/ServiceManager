package dao;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Address;
import dao.entity.Customer;
import dao.entity.Employee;
import dao.entity.ServiceCall;
import dao.service.CustomerDao;
import dao.service.EmployeeDao;
import dao.service.PersistenceManager;
import dao.service.ServiceCallDao;


public class ServiceCallDaoTest {
	
	@Before
	public void initEmfAndEm() {
		Logger.getLogger("org").setLevel(Level.SEVERE);
	}
	@After
	public void cleanUp(){
		PersistenceManager.cleanResources();
	}
	
	@Test
	public void testServiceCall() {
		EmployeeDao ed = new EmployeeDao();
		CustomerDao cd = new CustomerDao();
		ServiceCallDao scd = new ServiceCallDao();
		ServiceCall test = null;
		int key=0;
		try {
			//get or create employee
			List<Employee> employees1 = ed.findAllEmployees();
			Employee emp=null;
			if(employees1 != null && !employees1.isEmpty()){
				emp = employees1.get(0);
			}
			if(emp == null){
				Address a = new Address("Veldweg","103","2260","Westerlo");
				emp = new Employee("Jos", "Heylen", a, "jh@mail.com", "0488556699");	
				ed.insertEmployee(emp);
			}
			//get or create customer
			List<Customer> customers1 = cd.findAllCustomers();
			Customer cust=null;
			if(customers1 != null && !employees1.isEmpty()){
				cust = customers1.get(0);
			}
			if(cust == null){
				cust = new Customer("Stijn","Heylen","BE0822556699");	
				cd.insertCustomer(cust);
			}
			
			//Create a new call
			ServiceCall sc = new ServiceCall(cust, "new call to test", "longer description to test", emp);
			
			scd.insertServiceCall(sc);
			
			//check
			key = sc.getId();
			test = scd.findServiceCallByKey(key);
			assertTrue(sc!=null);
			assertTrue(test.getCustomer()!=null && test.getCustomer().getServiceCalls()!=null &&
					test.getCustomer().getServiceCalls().size()>0);
			assertTrue(test.getResponsible()!=null && test.getResponsible().getServiceCalls()!=null &&
					test.getResponsible().getServiceCalls().size()>0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			assertTrue(false);
		}
		assertTrue(true);
	}
	@Test
	public void testDeleteServiceCall() {
		
		CustomerDao cd = new CustomerDao();
		ServiceCallDao scd = new ServiceCallDao();
		ServiceCall test = null;
		Customer ctest = null;
		int key = 0;

		try {
			List<Customer> customers = cd.findCustomersByLastName("HeylenTest");
			if(customers == null || customers.isEmpty()){
				ctest = new Customer("Stijn","Heylen","BE0822556699");	
				cd.insertCustomer(ctest);
			}else{
				ctest = customers.get(0);
			}
			ServiceCall sc = null;
			if(ctest.getServiceCalls()==null || ctest.getServiceCalls().isEmpty()){
				sc = new ServiceCall(ctest, "new call to test", "longer description to test");
				scd.insertServiceCall(sc);
			}
			key = sc.getId();
			test = scd.findServiceCallByKey(key);
			scd.deleteServiceCall(test);
			test = null;
			test = scd.findServiceCallByKey(key);
			assertTrue(test == null);
		
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
