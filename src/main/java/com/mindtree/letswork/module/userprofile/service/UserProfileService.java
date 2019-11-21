package com.mindtree.letswork.module.userprofile.service;

import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;

/**
 * @author M1053435
 *
 */
@Service
public interface UserProfileService {

	/**
	 * This function return the User with the username 
	 * @param name
	 * @return User
	 */
	User getUserByName(String name);

	/**
	 * This function edits the email for the user that the username == name
	 * @param email
	 * @param name
	 * @return void
	 */
	void editUserEmail(String email, String name);
	
}
