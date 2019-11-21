package com.mindtree.letswork.module.userprofile.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.mindtree.letswork.module.authentication.dto.UserDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.userprofile.Utils.DTOUtils;
import com.mindtree.letswork.module.userprofile.service.UserProfileService;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTest {
	
	@InjectMocks
	UserProfileController controller;
	
	@Mock
	DTOUtils utility;
	
	@Mock
	UserProfileService service;

	@Test
	public void getUserByNameTest() {
		User newUser = new User();
		newUser.setUsername("Itachiark");
		when(service.getUserByName("Itachiark")).thenReturn(newUser);
		ResponseEntity<UserDTO> test = controller.getUserByName("Itachiark");
		UserDTO testDTO = (UserDTO) utility.convert(newUser, UserDTO.class);
		assertEquals(testDTO, test.getBody());
	}

}
