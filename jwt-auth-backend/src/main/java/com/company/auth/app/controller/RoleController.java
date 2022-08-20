package com.company.auth.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.auth.app.entity.Role;
import com.company.auth.app.service.RoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService service;

	@GetMapping("/getAllRoles")
	public ResponseEntity<?> getAllRoles() {
		List<Role> roles = this.service.getAllRoles();
		return (roles == null) ? new ResponseEntity<String>("No roles found", HttpStatus.INTERNAL_SERVER_ERROR)
				: ResponseEntity.ok(roles);
	}

}
