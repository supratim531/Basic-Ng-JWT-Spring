package com.company.auth.app.service;

import java.util.List;

import com.company.auth.app.entity.User;

public interface UserService {

	public List<User> getAllUsers();

	public User registerUser(User user);

	public User getUserById(Long userId);

	public Boolean deleteUserById(Long userId);

	public User getUserByUsername(String username);

}
