package dao;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.entity.Address;
import dao.service.AddressDao;
import dao.service.PersistenceManager;


public class AddressDaoTest {
	
	@Before
	public void initEmfAndEm() {
		Logger.getLogger("org").setLevel(Level.SEVERE);
	}
	@After
	public void cleanUp(){
		PersistenceManager.cleanResources();
	}

	@Test
	public void testBasic() {
		boolean ok = false;
		EntityManager em = PersistenceManager.getEntityManager();
		if(em!=null){
			ok = true;
		}
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
