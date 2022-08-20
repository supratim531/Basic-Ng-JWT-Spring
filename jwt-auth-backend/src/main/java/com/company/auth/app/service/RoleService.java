package com.company.auth.app.service;

import java.util.List;

import com.company.auth.app.entity.Role;

public interface RoleService {

	public List<Role> getAllRoles();

	public Role createRole(Role role);

	public Role getRoleById(String roleName);

}
