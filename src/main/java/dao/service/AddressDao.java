package dao.service;

import java.util.ArrayList;
import java.util.List;

import dao.entity.Address;

public class AddressDao extends DaoService {
	public void insertAddress(Address a) throws Exception{
		this.saveObject(a);
	}
	/**
	 * Selects all Addresss.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Address> findAllAddresss() throws Exception {
		List<Address> result = new ArrayList<Address>();

		List<Object> temp = this.getResultsList("findAllAddresses");
		if(temp != null && temp.size()>0){
			for(Object o : temp){
				if(o!=null)
					result.add((Address)o);
			}
		}

		return result;
	}
	/**
	 * Find an address with the primary key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Address findAddressByKey(int key) throws Exception{
		Address result = getEntityManager().find(Address.class, key);
		return result;
	}
	/**
	 * Removes the Address
	 * @param a
	 * @throws Exception
	 */
	public void deleteAddress(Address a) throws Exception{
		this.removeObject(a);
	}

}
