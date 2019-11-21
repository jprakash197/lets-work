package com.mindtree.letswork.module.userprofile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.repository.UserRepo;
import com.mindtree.letswork.module.userprofile.service.UserProfileService;

/**
 * @author M1053435
 *
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserRepo userRepo;

	@Override
	public User getUserByName(String name) {
		List<User> users = userRepo.findAll();
		User user = users
				.stream()
				.filter(thecurrentUser -> thecurrentUser.getUsername().compareTo(name) == 0)
				.findFirst()
				.orElse(null);
		return user;
	}

	@Override
	public void editUserEmail(String email, String name) {
		List<User> users = userRepo.findAll();
		User user = users
				.stream()
				.filter(thecurrentUser -> thecurrentUser.getUsername().compareTo(name) == 0)
				.findFirst()
				.orElse(null);
		user.setEmail(email);
		userRepo.save(user);
	}
	
}
