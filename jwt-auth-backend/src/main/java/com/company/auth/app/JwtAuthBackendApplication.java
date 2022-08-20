package com.company.auth.app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.auth.app.entity.Employee;
import com.company.auth.app.entity.Role;
import com.company.auth.app.entity.User;
import com.company.auth.app.service.EmployeeService;
import com.company.auth.app.service.RoleService;
import com.company.auth.app.service.UserService;

@SpringBootApplication
public class JwtAuthBackendApplication {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthBackendApplication.class, args);
		System.out.println("----- (USER, ADMIN) 2 roles are created -----");
		System.out.println("----- 4 users are registered -----");
		System.out.println("----- 5 employees are registered -----");
		System.out.println("----- Backend Server Running Successfully -----");
	}

	@PostConstruct
	public void dummy() {
		Role user = Role.builder().roleName("ROLE_USER").roleDescription("This role is for user only").build();
		Role admin = Role.builder().roleName("ROLE_ADMIN").roleDescription("This role is for superuser only").build();
		this.roleService.createRole(user);
		this.roleService.createRole(admin);

		User user0 = User.builder().email("harry@hogwarts.com").username("admin").password("admin@webapp")
				.firstName("Harry").lastName("Potter").role(admin).build();
		User user1 = User.builder().email("amitava@gmail.com").username("amitava").password("admin@webapp")
				.firstName("Amitava").lastName("Chatterjee").role(admin).build();
		User user2 = User.builder().email("aniket@gmail.com").username("aniket").password("admin@webapp")
				.firstName("Aniket").lastName("Shaw").role(admin).build();
		User user3 = User.builder().email("sayan@gmail.com").username("sayan").password("admin@webapp")
				.firstName("Supratim").lastName("Majumder").role(admin).build();
		User user4 = User.builder().email("ankan@gmail.com").username("ankan").password("ankan123").firstName("Ankan")
				.lastName("Paul").role(user).build();
		this.userService.registerUser(user0);
		this.userService.registerUser(user1);
		this.userService.registerUser(user2);
		this.userService.registerUser(user3);
		this.userService.registerUser(user4);

		Employee employee1 = Employee.builder().fullName("SCOTT").address("USA").email("scott@gmail.com")
				.phone(9163681672L).build();
		Employee employee2 = Employee.builder().fullName("ADAMS").address("USA").email("adams@gmail.com")
				.phone(9163681673L).build();
		Employee employee3 = Employee.builder().fullName("JOHN").address("USA").email("john@gmail.com")
				.phone(9163681674L).build();
		Employee employee4 = Employee.builder().fullName("MARTHA").address("USA").email("martha@gmail.com")
				.phone(9163681675L).build();
		Employee employee5 = Employee.builder().fullName("CHEKHOV").address("USA").email("chekhov@gmail.com")
				.phone(9163681676L).build();
		this.employeeService.registerEmployee(employee1);
		this.employeeService.registerEmployee(employee2);
		this.employeeService.registerEmployee(employee3);
		this.employeeService.registerEmployee(employee4);
		this.employeeService.registerEmployee(employee5);
	}

}
