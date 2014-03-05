package dao.service;

import dao.entity.ContactType;

public class ContactTypeCST {
	ContactDao dao = new ContactDao();
	
	public ContactType EMAIL() {
		try {
			return dao.findContactType("Email");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ContactType("Email");
	}
	public ContactType MOBILE() {
		try {
			return dao.findContactType("Mobile");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ContactType("Mobile");
	}
	public ContactType FIXPHONE() {
		try {
			return dao.findContactType("FixPhone");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ContactType("FixPhone");
	}
}
