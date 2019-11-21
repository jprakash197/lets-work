package com.mindtree.letswork.module.venue.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DTOUtil {

	@Autowired
	private ModelMapper modelMapper;

	public Object convert(Object object, Class<?> type) {
		return modelMapper.map(object, type);
	}
}
