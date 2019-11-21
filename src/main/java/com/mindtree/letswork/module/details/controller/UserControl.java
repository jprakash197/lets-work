package com.mindtree.letswork.module.details.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.details.dto.UserDto;
import com.mindtree.letswork.module.details.entity.UserDetails;
import com.mindtree.letswork.module.details.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")

public class UserControl {
	
	@Autowired
	private UserService userService;
	

	
	@GetMapping("/getAll")
	public List<UserDto> getAll() 
	{
		List<UserDetails> userDetails= userService.getAllUsers();
		
		List<UserDto> Userdto =new ArrayList<>();
		try
		{
		for(UserDetails userdto:userDetails)
		{
			UserDto dto=new UserDto();
			dto.setId(userdto.getId());
			dto.setName(userdto.getName());
			dto.setEmail_id(userdto.getEmail_id());
			dto.setPhone_number(userdto.getPhone_number());
		
			
			
			byte[] encodeBase64=Base64Utils.encode(userdto.getPrimaryImage());
			String base64Encoded=new String(encodeBase64, "UTF-8");
			String file="data:image/"+"jpg"+";base64,"+base64Encoded;
			dto.setPrimaryImage(file);
			Userdto.add(dto);
			
		}
		return Userdto;
		}
		catch (Exception e) {
			return null;
			
		}
	}
	
	

}
