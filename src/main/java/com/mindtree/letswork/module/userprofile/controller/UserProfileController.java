package com.mindtree.letswork.module.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.authentication.dto.UserDTO;
import com.mindtree.letswork.module.userprofile.Utils.DTOUtils;
import com.mindtree.letswork.module.userprofile.service.UserProfileService;

@RestController
@CrossOrigin
public class UserProfileController {

	@Autowired
	UserProfileService profileService;

	@Autowired
	DTOUtils utility;

	@GetMapping("/getUser/{name}")
	public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
		System.out.println(name);
		UserDTO currentUser = (UserDTO) utility.convert(profileService.getUserByName(name), UserDTO.class);
		if(currentUser == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body(currentUser);
	}

//	@PostMapping("/addUser")
//	public void addUser(@RequestBody UserDTO userDTO) {
//		System.out.println(profileService.addUser((User) utility.convert(userDTO, User.class)));
//	}

	@PutMapping("getUser/{name}")
	public void editEmail(@RequestBody String email, @PathVariable String name) {
		profileService.editUserEmail(email, name);
	}

}
