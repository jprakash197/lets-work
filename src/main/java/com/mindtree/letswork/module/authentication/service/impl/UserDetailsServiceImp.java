package com.mindtree.letswork.module.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.service.AuthService;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	AuthService service; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.login(username); 
		if (user == null) {
			throw new UsernameNotFoundException("User does not exist.");
		}
		return user;
	}
	
}
