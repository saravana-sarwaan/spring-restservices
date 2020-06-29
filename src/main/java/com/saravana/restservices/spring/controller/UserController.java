package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path = "/findAll")
	public List<User> findAll() {
		return userService.getAllUsers();
	}
	
	@PostMapping(path = "/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping(path = "/find/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.findUser(id);
		
	}
	
	@PutMapping("/update")
	public String updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
	@GetMapping(path = "/findByUser/{username}")
	public User findByUsername (@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}

}
