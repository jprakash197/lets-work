package com.mindtree.letswork.module.authentication.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;
import com.mindtree.letswork.module.authentication.repository.UserRepo;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.security.JwtCreator;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JwtCreator creator;

	@Override
	public User login(String username) {
		return repo.findUserByUserName(username);
	}

	@Override
	public User authenticatePassword(String password, User user) throws IncorrectPasswordException {
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new IncorrectPasswordException("Password mismatch.");

		return user;

	}

	@Override
	public User signup(User user) throws InvalidReferralCodeException, InvalidInputException {
		if (user.getReferredCode() != null)
			validateReferralCode(user.getReferredCode());

		if (isUsernameAvailable(user.getUsername())) {
			String token = creator.generateJwtToken(user);
			user.setToken(token);
			user.setRole("USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return repo.save(user);
		} else {
			throw new InvalidInputException("Invalid Username. Username already taken. Sign Up could not be completed");
		}
	}

	@Override
	public boolean validateReferralCode(String referralCode) throws InvalidReferralCodeException {
		Optional<User> user = repo.findByStringID(referralCode);
		if (user.isPresent()) {
			return true;
		} else {
			throw new InvalidReferralCodeException("Invalid Referral Code. Sign Up could not be completed");
		}
	}

	@Override
	public boolean isUsernameAvailable(String username) {
		User user = repo.findUserByUserName(username);
		if (user != null)
			return false;

		return true;

	}

	@Override
	public boolean updateToken(String token, User user) throws InvalidJWTToken {
		int rows = repo.updateToken(token, user.getReferralCode());
		if (rows != 0) {
			return true;
		} else {
			throw new InvalidJWTToken("Server Error: Unable to save Jason Web Token.");
		}
	}

}
