package com.company.auth.app.service;

import java.util.List;

import com.company.auth.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long employeeId);

	public Employee registerEmployee(Employee employee);

}
