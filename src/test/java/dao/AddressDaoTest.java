package dao;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import dao.entity.Address;
import dao.service.AddressDao;


public class AddressDaoTest {
	
	@Before
	public void initEmfAndEm() {
		Logger.getLogger("org").setLevel(Level.SEVERE);
	}

	@Test
	public void testBasic() {
		boolean ok = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("serviceManagerPU");
		EntityManager em = emf.createEntityManager();
		if(em!=null){
			ok = true;
		}
			
		if(em!=null && em.isOpen())
			em.close();
		if(emf!=null && emf.isOpen())
			emf.close();
		assertTrue(ok);
	}
	@Test
	public void testAddress() {
		AddressDao ad = new AddressDao();
		Address a = new Address("Veldweg","103","2260","Westerlo");
		Address test = null;
		try {
			ad.insertAddress(a);
			int key = a.getId();
			if(key > 0){
				test = ad.findAddressByKey(key);
			}
			assertTrue(test != null);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
