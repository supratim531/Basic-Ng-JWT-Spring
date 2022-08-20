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

import com.company.auth.app.entity.Employee;
import com.company.auth.app.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> employees = this.service.getAllEmployees();
		return (employees == null) ? new ResponseEntity<String>("No employees found", HttpStatus.INTERNAL_SERVER_ERROR)
				: ResponseEntity.ok(employees);
	}

	@PostMapping("/registerEmployee")
	public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
		try {
			Employee _employee = this.service.registerEmployee(employee);
			return ResponseEntity.status(201).body(_employee);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Bad request sent");
		}
	}

}
