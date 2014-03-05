package dao.service;

import java.util.ArrayList;
import java.util.List;

import dao.entity.ServiceCall;

public class ServiceCallDao extends DaoService {
	public void insertServiceCall(ServiceCall call) throws Exception {
		this.saveObject(call);
	}

	/**
	 * Selects all Service calls.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ServiceCall> findAllServiceCalls() throws Exception {
		List<ServiceCall> result = new ArrayList<ServiceCall>();
		List<Object> temp = this.getResultsList("findAllServiceCalls");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((ServiceCall)o);
			}
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
		ServiceCall result = getEntityManager().find(ServiceCall.class, key);
		return result;
	}
	public void deleteServiceCall(ServiceCall sc) throws Exception{
		this.removeObject(sc);
	}
	
}

