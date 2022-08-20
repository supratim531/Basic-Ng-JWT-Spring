package com.company.auth.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.auth.app.entity.Employee;
import com.company.auth.app.repository.EmployeeRepository;
import com.company.auth.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = this.repository.findAll();
		return (employees.size() > 0) ? employees : null;
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee registerEmployee(Employee employee) {
		Employee _employee = this.repository.save(employee);
		return _employee;
	}

}
