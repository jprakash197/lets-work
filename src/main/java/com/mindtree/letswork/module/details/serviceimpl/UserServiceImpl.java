package com.mindtree.letswork.module.details.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.details.entity.UserDetails;
import com.mindtree.letswork.module.details.repository.User1Repository;
import com.mindtree.letswork.module.details.service.UserService;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private User1Repository trackRepository;

	@Override
	public List<UserDetails> getAllUsers() {
		return trackRepository.findAll();
	}
}
