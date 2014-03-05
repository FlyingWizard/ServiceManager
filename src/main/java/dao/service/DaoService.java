package dao.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Base Dao class to provide all basic Eclipselink operation towards the DB
 * @author Stijn Heylen
 *
 */
public abstract class DaoService {
	protected EntityTransaction tx;
	
	protected EntityManager getEntityManager(){
		return PersistanceManager.getEntityManager();
	}

	protected void initTransaction() {
		// Get a transaction and persist the book
		tx = getEntityManager().getTransaction();
	}

	public void saveObject(Object obj) throws Exception {

		try {

			getEntityManager().getTransaction().begin();

			getEntityManager().persist(obj);

			getEntityManager().getTransaction().commit();

		} catch (Exception e) {

			if (getEntityManager().getTransaction().isActive()) {

				getEntityManager().getTransaction().rollback();

			}

			throw e;

		}

	}
	public void removeObject(Object obj) throws Exception {

		try {

			getEntityManager().getTransaction().begin();

			//Check to verify if the object is managed
			if(!getEntityManager().contains(obj))
				getEntityManager().merge(obj);
				
			getEntityManager().remove(obj);

			getEntityManager().getTransaction().commit();

		} catch (Exception e) {

			if (getEntityManager().getTransaction().isActive()) {

				getEntityManager().getTransaction().rollback();

			}

			throw e;

		}

	}

	protected Object getSingleResultSet(String namedQuery) {

		Query query = null;

		try {

			query = getEntityManager().createNamedQuery(namedQuery);

		} catch (Exception ex) {

			return ex;

		}

		return query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	protected List<Object> getResultsList(String namedQuery) throws Exception {

		Query query = null;

		try {

			query = getEntityManager().createNamedQuery(namedQuery);

		} catch (Exception ex) {

			throw ex;

		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	protected List<Object> getpaginatedResultList(String namedQuery, int startIndex,
			int maxResult) throws Exception {

		Query query = null;

		try {

			query = getEntityManager().createNamedQuery(namedQuery).setFirstResult(startIndex)
					.setMaxResults(maxResult);

		} catch (Exception ex) {

			throw ex;

		}
		return query.getResultList();
	}
}
