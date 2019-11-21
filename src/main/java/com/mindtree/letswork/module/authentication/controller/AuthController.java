package com.mindtree.letswork.module.authentication.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mindtree.letswork.module.authentication.dto.UserInputDTO;
import com.mindtree.letswork.module.authentication.dto.UserOutputDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.security.JwtCreator;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	AuthService service;

	@Autowired
	UserDetailsService detailsService;

	@Autowired
	JwtCreator creator;

	@GetMapping("/login/{username}&{password}")
	public UserOutputDTO login(@Valid @PathVariable String username, @Valid @PathVariable String password)
			throws IncorrectPasswordException, UsernameNotFoundException, InvalidJWTToken {

		User user = (User) detailsService.loadUserByUsername(username);
		user = service.authenticatePassword(password, user);
		String token = creator.generateJwtToken(user);
		service.updateToken(token, user);

		UserOutputDTO userOutput = new UserOutputDTO();
		userOutput.setToken(token);
		userOutput.setRole(user.getRole());
		return userOutput;
	}

	@PostMapping("/signup")
	public UserOutputDTO signup(@Valid @RequestBody UserInputDTO userDTO)
			throws IOException, InvalidReferralCodeException, InvalidInputException {
		User user = mapOntoUser(userDTO);
		user = service.signup(user);
		UserOutputDTO userOutput = new UserOutputDTO();
		userOutput.setToken(user.getToken());
		userOutput.setRole(user.getRole());
		return userOutput;
	}

	public User mapOntoUser(UserInputDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setRealName(userDTO.getRealName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		System.out.println(userDTO.getReferredCode());
		user.setReferredCode(userDTO.getReferredCode());
		System.out.println(user.getReferredCode());
		return user;
	}


	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
