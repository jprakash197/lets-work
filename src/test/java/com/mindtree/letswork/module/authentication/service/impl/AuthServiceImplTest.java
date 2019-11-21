package com.mindtree.letswork.module.authentication.service.impl;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;
import com.mindtree.letswork.module.authentication.repository.UserRepo;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.security.JwtCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceImplTest {


	@Autowired
	AuthService service;

	@MockBean
	UserRepo userRepo;

	@MockBean
	PasswordEncoder passwordEncoder;

	@MockBean
	JwtCreator creator;

	@Test
	public void loginValid() {
		User user = new User();
		user.setUsername("MockUsername");
		Mockito.when(userRepo.findUserByUserName("MockUsername")).thenReturn(user);
		assertEquals(user, service.login("MockUsername"));
	}
	
	@Test 
	public void loginInvalid() {
		User user = new User();
		user.setUsername("MockUsername");
		User user2 = new User();
		user2.setUsername("DifferentUsername");
		Mockito.when(userRepo.findUserByUserName("MockUsername")).thenReturn(user);

		assertNotEquals(user2, service.login("Mockusername"));
	}

	@Test
	public void authenticatePasswordValid() throws IncorrectPasswordException {
		String originalPassword = "Password123";
		User user = new User();
		user.setPassword(passwordEncoder.encode(originalPassword));
		Mockito.when(passwordEncoder.matches(originalPassword, user.getPassword())).thenReturn(true);

		assertEquals(user, service.authenticatePassword(originalPassword, user));
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void authenticatePasswordInvalid() throws IncorrectPasswordException {
		String originalPassword = "Password123";
		User user = new User();
		user.setPassword(passwordEncoder.encode(originalPassword));
		String wrongPassword = "passWord456";
		Mockito.when(passwordEncoder.matches(originalPassword, user.getPassword())).thenReturn(true);

		assertNotEquals(user, service.authenticatePassword(wrongPassword, user));
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void authenticatePasswordException() throws IncorrectPasswordException {
		String originalPassword = "Password123";
		User user = new User();
		user.setPassword(passwordEncoder.encode(originalPassword));
		String wrongPassword = "passWord456";
		Mockito.when(passwordEncoder.matches(originalPassword, user.getPassword())).thenReturn(true);

		assertEquals("Password mismatch.", service.authenticatePassword(wrongPassword, user));
	}

	@Test
	public void validateReferralCodeValid() throws InvalidReferralCodeException {
		String refferedCode = "ref1234";
		User user = new User();
		Optional<User> opUser = Optional.of((User) user);
		Mockito.<Optional<User>>when(userRepo.findByStringID(refferedCode)).thenReturn(opUser);

		assertEquals(true, service.validateReferralCode(refferedCode));
	}
	
	@Test(expected = InvalidReferralCodeException.class)
	public void validateReferralCodeInvalid() throws InvalidReferralCodeException {
		String refferedCode = "ref1234";
		String wrongRefferedCode = "ref4567";
		User user = new User();
		Optional<User> opUser = Optional.of((User) user);
		Mockito.<Optional<User>>when(userRepo.findByStringID(refferedCode)).thenReturn(opUser);

		assertNotEquals(true, service.validateReferralCode(wrongRefferedCode));
	}
	
	@Test(expected = InvalidReferralCodeException.class)
	public void validateReferralCodeException() throws InvalidReferralCodeException {
		String refferedCode = "ref1234";
		String wrongRefferedCode = "ref4567";
		User user = new User();
		Optional<User> opUser = Optional.of((User) user);
		Mockito.<Optional<User>>when(userRepo.findByStringID(refferedCode)).thenReturn(opUser);

		assertEquals("Invalid Referral Code. Sign Up could not be completed",
				service.validateReferralCode(wrongRefferedCode));
	}

	@Test
	public void isUsernameAvailableValidFalse() {
		String username = "MockUsername";
		User user = new User();
		user.setUsername(username);
		Mockito.when(userRepo.findUserByUserName(username)).thenReturn(user);

		assertEquals(false, service.isUsernameAvailable(username));
		} 
	
	@Test
	public void isUsernameAvailableValidTrue() {
		String username = "MockUsername";
		String wrongUsername = "DifferentUsername";
		User user = new User();
		user.setUsername(username);
		Mockito.when(userRepo.findUserByUserName(username)).thenReturn(user);

		assertEquals(true, service.isUsernameAvailable(wrongUsername));
	} 
	
	@Test
	public void isUsernameAvailableInvalid() {
		String username = "MockUsername";
		String wrongUsername = "DifferentUsername";
		User user = new User();
		user.setUsername(username);
		Mockito.when(userRepo.findUserByUserName(username)).thenReturn(user);

		assertNotEquals(false, service.isUsernameAvailable(wrongUsername));
	} 

	@Test
	public void signupValid() throws InvalidInputException, InvalidReferralCodeException {
		User user = new User();
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(creator.generateJwtToken(user)).thenReturn(token);
		user.setToken(token);
		Mockito.when(userRepo.save(user)).thenReturn(user);

		assertEquals(user, service.signup(user));
	}
	
	@Test
	public void signupInvalid() throws InvalidInputException, InvalidReferralCodeException {
		User user = new User();
		User user2 = new User(); 
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(creator.generateJwtToken(user)).thenReturn(token);
		user.setToken(token);
		Mockito.when(userRepo.save(user)).thenReturn(user);

		assertNotEquals(user, service.signup(user2));
		assertNotEquals(user2, service.signup(user));
	}
	 
	@Test
	public void updateTokenValid() throws InvalidJWTToken {
		User user = new User();
		user.setReferralCode("Ref1234");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(userRepo.updateToken(token, user.getReferralCode())).thenReturn(1);
		
		assertNotEquals(false, service.updateToken(token, user));
	}
	
	@Test
	public void updateTokenInvalid() throws InvalidJWTToken {
		User user = new User();
		user.setReferralCode("Ref1234");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(userRepo.updateToken(token, user.getReferralCode())).thenReturn(1);
		
		assertNotEquals(false, service.updateToken(token, user));
	}
	
	@Test(expected = InvalidJWTToken.class)
	public void updateTokenException() throws InvalidJWTToken {
		User user = new User();
		user.setReferralCode("Ref1234");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(userRepo.updateToken(token, user.getReferralCode())).thenReturn(0);
		
		assertEquals("Server Error: Unable to save Jason Web Token.", service.updateToken(token, user));
	}
	
}
