package com.company.auth.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.auth.app.entity.Role;
import com.company.auth.app.repository.RoleRepository;
import com.company.auth.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = this.repository.findAll();
		return (roles.size() > 0) ? roles : null;
	}

	@Override
	public Role createRole(Role role) {
		Role _role = this.repository.save(role);
		return _role;
	}

	@Override
	public Role getRoleById(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

}
