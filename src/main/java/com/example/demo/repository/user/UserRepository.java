package com.example.demo.repository.user;

import java.util.Optional;

import com.example.demo.model.user.User;

public interface UserRepository {
	Optional<User> findByEmail(String email);
	int create(User user);
}
