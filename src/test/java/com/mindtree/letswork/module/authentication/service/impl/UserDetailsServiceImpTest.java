package com.mindtree.letswork.module.authentication.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.repository.UserRepo;
import com.mindtree.letswork.module.authentication.service.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImpTest {

	
	@Autowired
	UserDetailsServiceImp details;
	
	@MockBean
	AuthService service; 
	
	@MockBean
	UserRepo repo; 
	
	@Test
	public void loadUserByUsernameValid() throws UsernameNotFoundException {
		User user = new User(); 
		String username = "MockUsername";
		user.setUsername(username);
		Mockito.when(service.login("MockUsername")).thenReturn(user);	
		assertEquals(user, details.loadUserByUsername(username));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void loaduserByUsernameValidException() {
		User user = new User(); 
		String username = "MockUsername";
		user.setUsername(username);
		User user2 = new User();
		user2.setUsername("DifferentUsername");
		Mockito.when(service.login(username)).thenReturn(user);
		
		assertEquals("User does not exist.", details.loadUserByUsername("DifferentUsername"));
	}
	
	@Test
	public void loaduserByUsernameInvalid() {
		User user = new User(); 
		String username = "MockUsername";
		user.setUsername(username);
		User user2 = new User();
		user2.setUsername("DifferentUsername");
		Mockito.when(service.login(username)).thenReturn(user);
		
		assertNotEquals(user2, details.loadUserByUsername(username));
	}
	

}
