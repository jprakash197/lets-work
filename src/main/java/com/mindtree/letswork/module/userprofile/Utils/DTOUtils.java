package com.mindtree.letswork.module.userprofile.Utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DTOUtils {

	@Autowired 
	private ModelMapper mapper;
	
	public Object convert(Object something, Class<?> sometype) {
		return mapper.map(something, sometype);
	}
	
	
}
