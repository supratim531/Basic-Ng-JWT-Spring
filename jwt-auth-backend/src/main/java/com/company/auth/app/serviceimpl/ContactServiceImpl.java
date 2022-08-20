package com.company.auth.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.auth.app.entity.Contact;
import com.company.auth.app.repository.ContactRepository;
import com.company.auth.app.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contacts = this.repository.findAll();
		return (contacts.size() > 0) ? contacts : null;
	}

	@Override
	public Contact addContact(Contact contact) {
		Contact _contact = this.repository.save(contact);
		return _contact;
	}

}
