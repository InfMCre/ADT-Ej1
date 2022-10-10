package com.example.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.user.User;
import com.example.demo.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public int create(User user) {
		return userRepository.create(user);
	}
	
}
