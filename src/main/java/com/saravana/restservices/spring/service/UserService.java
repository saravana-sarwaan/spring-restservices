package com.saravana.restservices.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saravana.restservices.spring.exception.UserExistsException;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.exception.UsernameNotFoundException;
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
	public User createUser(User user) throws UserExistsException{
		if(userRepository.findByUserName(user.getUserName()) != null)
			throw new UserExistsException("User already exists");
		return userRepository.save(user);	
	}
	
	//To find existing user by their Id
	public Optional<User> findUser(Long id) throws UserNotFoundException{
		if(userRepository.findById(id).isPresent())
			return userRepository.findById(id);
		else
			throw new UserNotFoundException("User not found in the repository");
	}
	
	//To update user details if they exist
	public User updateUser(User user) throws UserNotFoundException{
		if(!userRepository.findById(user.getId()).isPresent())
			throw new UserNotFoundException("User not found");
		return userRepository.save(user);
	}
	
	//To delete user by their Id
	public void deleteUser(Long id)  {
		if(!userRepository.findById(id).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in the repository");
		userRepository.deleteById(id);
	}
	
	//To find user by their username
	public User findByUsername(String username) throws UsernameNotFoundException {
		if(userRepository.findByUserName(username) != null)
			return userRepository.findByUserName(username);
		else 
			throw new UsernameNotFoundException(username + " not found");
	}
	
}
