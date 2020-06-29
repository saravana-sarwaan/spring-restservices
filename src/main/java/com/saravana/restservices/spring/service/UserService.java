package com.saravana.restservices.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	//To get all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//To create a new user
	public User createUser(User user) {
		return userRepository.save(user);	
	}
	
	//To find existing user by their Id
	public Optional<User> findUser(Long id) {
		return userRepository.findById(id);
	}
	
	//To update user details if they exist
	public String updateUser(User user) {
		if(userRepository.findById(user.getId()).isPresent()) { 
			userRepository.save(user);
			return "User updated";
		} else {
			return "User not found";
		}
	}
	
	//To delete user by their Id
	public void deleteUser(Long id) {
		if(userRepository.findById(id).isPresent())
			userRepository.deleteById(id);
	}
	
	//To find user by their username
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
	
}
