package dao;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Address;
import dao.entity.Contact;
import dao.entity.Customer;
import dao.service.ContactDao;
import dao.service.ContactTypeCST;
import dao.service.CustomerDao;
import dao.service.PersistenceManager;


public class CustomerDaoTest {
	
	@Before
	public void initEmfAndEm() {
		Logger.getLogger("org").setLevel(Level.SEVERE);
	}
	@After
	public void cleanUp(){
		PersistenceManager.cleanResources();
	}

	@Test
	public void testCustomer() {
		Address a = new Address("Veldweg","103","2260","Westerlo");
		CustomerDao cd = new CustomerDao();
		Customer c = new Customer("Stijn","HeylenTest","BE0822556699",a);
		Customer test = null;
		int key = 0;

		try {
			//Address will be cacade peristed - because set like this in entity - default is nothing cascaded!
			cd.insertCustomer(c);
			key = c.getId();
			if(key > 0)
				test = cd.findCustomerByKey(key);
			assertTrue(test != null);
			assertTrue(test.getInvoiceAddress()!=null && test.getInvoiceAddress().getId()>0);
			assertTrue(test.getDeliveryAdresses()!= null && test.getDeliveryAdresses().size()==1);
			
			List<Customer> customers = cd.findAllCustomers();
			assertTrue(customers!=null && customers.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	@Test
	public void testContact() {
		CustomerDao cd = new CustomerDao();
		Customer test = null; 
		Contact test2 = null;
		int key = 0;
		int key2 = 0;
		
		try {
			
			Customer c = new Customer("Stijn","HeylenTest","BE0822556699");
			ContactDao ctcd = new ContactDao();
			ContactTypeCST helper = new ContactTypeCST();	
			c.addContact("sh@gmail.com",helper.EMAIL() );

			cd.insertCustomer(c);
			key = c.getId();
			
			test = cd.findCustomerByKey(key);
			assertTrue(test.getContactDetails() != null && test.getContactDetails().size()==1);

			key2 = c.getContactDetails().get(0).getId();
			test2 = ctcd.findContactByKey(key2);
			assertTrue(test2 != null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	@Test
	public void testDeleteCustomer() {
		
		CustomerDao cd = new CustomerDao();
		Customer test = null;
		int key = 0;

		try {
			List<Customer> customers = cd.findCustomersByLastName("HeylenTest");
			if(customers.isEmpty()){
				test = new Customer("Stijn","HeylenTest","BE0822556699");
				cd.saveObject(test);
			}else{
				test = customers.get(0);
			}
			key = test.getId();
			cd.deleteCustomer(test);
			test = null;
			test = cd.findCustomerByKey(key);
			assertTrue(test == null);
			for(Customer  c : cd.findCustomersByLastName("HeylenTest")){
				cd.deleteCustomer(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
