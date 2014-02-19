package dao.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DaoService {
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;

	protected void initEntityManager() {
		// Get an Entitymanager
		emf = Persistence.createEntityManagerFactory("serviceManagerPU");
		em = emf.createEntityManager();
	}

	protected void initTransaction() {
		// Get a transaction and persist the book
		tx = em.getTransaction();
	}

	protected void cleanResources() {
		// Close resources
		if(em!=null && em.isOpen())
			em.close();
		if(emf!=null && emf.isOpen())
			emf.close();
	}
}
