package com.company.auth.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.auth.app.entity.User;
import com.company.auth.app.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = this.service.getAllUsers();
		return (users == null) ? new ResponseEntity<String>("No users found", HttpStatus.INTERNAL_SERVER_ERROR)
				: ResponseEntity.ok(users);
	}

	@GetMapping("/getUserByUsername")
	public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
		User user = this.service.getUserByUsername(username);
		return (user == null)
				? new ResponseEntity<String>("No user (" + username + ") found", HttpStatus.INTERNAL_SERVER_ERROR)
				: ResponseEntity.ok(user);
	}

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			User _user = this.service.registerUser(user);
			return ResponseEntity.status(201).body(_user);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Email or Username is already registered");
		}
	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long userId) {
		Boolean deleteStatus = this.service.deleteUserById(userId);
		return (deleteStatus) ? ResponseEntity.ok("yes")
				: new ResponseEntity<String>("no", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
