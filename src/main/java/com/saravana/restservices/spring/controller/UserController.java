package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.saravana.restservices.spring.exception.UserExistsException;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.exception.UsernameNotFoundException;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
@Validated
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path = "/findAll")
	public List<User> findAll() {
		return userService.getAllUsers();
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(header, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping(path = "/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			return userService.findUser(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		try {
			return userService.updateUser(user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
	@GetMapping(path = "/findByUser/{username}")
	public User findByUsername (@PathVariable("username") String username) throws UsernameNotFoundException {
		try {
			return userService.findByUsername(username);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());	
		}
	}

}
