package com.mindtree.letswork.module.authentication.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.dto.UserInputDTO;
import com.mindtree.letswork.module.authentication.dto.UserOutputDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;
import com.mindtree.letswork.module.authentication.repository.UserRepo;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.module.venue.util.DTOUtil;
import com.mindtree.letswork.security.JwtCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {
	
	@Autowired
	AuthController controller;

	@Autowired
	UserRepo repo;

	@MockBean
	AuthService service;

	@MockBean
	UserDetailsService detailsService;

	@MockBean
	JwtCreator creator;

	@MockBean
	DTOUtil dtoUtil;

	@Test
	public void login() throws IncorrectPasswordException, UsernameNotFoundException, InvalidJWTToken {
		User user = new User();
		user.setUsername("MockUsername");
		user.setPassword("M10534adeT");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU2ODIwNDI5MiwiZXhwIjoxNTY4MjA0NDEyfQ.9F__uC8YYLtwY-URoKZUwQMNfymoJK0mWntxYyekX6YmVL7AHdFxLS0mLKEVwHyY-qzHu-En5G6gK-CS1S4uAg";
		Mockito.when(detailsService.loadUserByUsername("MockUsername")).thenReturn(user);
		Mockito.when(service.authenticatePassword("M10534adeT", user)).thenReturn(user);
		Mockito.when(creator.generateJwtToken(user)).thenReturn(token);
		UserOutputDTO userDTO = controller.login("MockUsername", "M10534adeT");

		assertNotNull(userDTO);
		assertEquals(token, userDTO.getToken());
	}

	@Test
	public void mapOntoUserValid() {
		UserInputDTO userDTO = new UserInputDTO();
		String password = "ThisIsAPassword123";
		String email = "email@email.com";
		String username = "Username";
		String name = "This Name";
		String ref = "Ref1234456";
		userDTO.setEmail(email);
		userDTO.setRealName(name);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setReferredCode(ref);

		User user = controller.mapOntoUser(userDTO);

		assertNotNull(user);
		assertEquals(user.getPassword(), userDTO.getPassword());
		assertEquals(user.getEmail(), userDTO.getEmail());
		assertEquals(user.getUsername(), userDTO.getUsername());
		assertEquals(user.getRealName(), userDTO.getRealName());
		assertEquals(user.getReferredCode(), userDTO.getReferredCode());
	}

	@Test
	public void signupValid() throws InvalidReferralCodeException, InvalidInputException, IOException {
		UserInputDTO userDTO = new UserInputDTO();
		String password = "ThisIsAPassword123";
		String email = "email@email.com";
		String username = "Username";
		String name = "This Name";
		String ref = "Ref1234456";
		userDTO.setEmail(email);
		userDTO.setRealName(name);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setReferredCode(ref);
		User user = new User();
		user.setEmail(email);
		user.setRealName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setReferredCode(ref);

		Mockito.when(service.signup(user)).thenReturn(user);

		UserOutputDTO userODTO = controller.signup(userDTO);

		assertNotNull(userODTO);
		assertFalse(userODTO.getToken() != null);

	}

}
