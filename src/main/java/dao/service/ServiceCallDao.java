package dao.service;

import java.util.List;

import dao.entity.Address;
import dao.entity.ServiceCall;

public class ServiceCallDao extends DaoService {
	public void insertServiceCall(ServiceCall call) throws Exception {

		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			this.initTransaction();

			tx.begin();
			em.persist(call);
			tx.commit();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

	}

	/**
	 * Selects all Service calls.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceCall> findAllServiceCalls() throws Exception {
		List<ServiceCall> result = null;
		try {

			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}

			result = em.createNamedQuery("findAllServiceCalls").getResultList();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			this.cleanResources();
		}

		return result;
	}
	/**
	 * Find an ServiceCall with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ServiceCall findServiceCallByKey(int key) throws Exception{
		ServiceCall result = null;
		try {
			if (em == null || !em.isOpen()) {
				this.initEntityManager();
			}
			result = em.find(ServiceCall.class, key);
		} catch (Exception e) {
			throw e;
		}
		finally {
			this.cleanResources();
		}
		return result;
	}
}

