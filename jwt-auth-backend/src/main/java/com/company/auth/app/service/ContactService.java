package com.company.auth.app.service;

import java.util.List;

import com.company.auth.app.entity.Contact;

public interface ContactService {

	public List<Contact> getAllContacts();

	public Contact addContact(Contact contact);

}
