package com.company.auth.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.auth.app.entity.Contact;
import com.company.auth.app.service.ContactService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private ContactService service;

	@GetMapping("/getAllContacts")
	public ResponseEntity<?> getAllContacts() {
		List<Contact> contacts = this.service.getAllContacts();
		return (contacts == null) ? new ResponseEntity<String>("No contacts found", HttpStatus.INTERNAL_SERVER_ERROR)
				: ResponseEntity.ok(contacts);
	}

	@PostMapping("/addContact")
	public ResponseEntity<?> addContact(@RequestBody Contact contact) {
		try {
			Contact _contact = this.service.addContact(contact);
			return ResponseEntity.status(201).body(_contact);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Bad request sent");
		}
	}

}
