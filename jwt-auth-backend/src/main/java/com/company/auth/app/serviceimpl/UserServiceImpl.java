package com.company.auth.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.auth.app.entity.User;
import com.company.auth.app.repository.UserRepository;
import com.company.auth.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getAllUsers() {
		List<User> users = this.repository.findAll();
		return (users.size() > 0) ? users : null;
	}

	@Override
	public User registerUser(User user) {
		User _user = this.repository.save(user);
		return _user;
	}

	@Override
	public User getUserById(Long userId) {
		User user = this.repository.findById(userId).orElse(null);
		return user;
	}

	@Override
	public Boolean deleteUserById(Long userId) {
		User user = this.getUserById(userId);

		if (user == null)
			return false;

		this.repository.deleteById(userId);
		return true;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = this.repository.findByUsername(username).orElse(null);
		return user;
	}

}
