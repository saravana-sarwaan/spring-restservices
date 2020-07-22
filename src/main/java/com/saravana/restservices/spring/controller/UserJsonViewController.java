package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.model.View;
import com.saravana.restservices.spring.service.UserService;

@RestController
@RequestMapping("/jsonView/user")
@Validated
public class UserJsonViewController {
	
	@Autowired
	public UserService userService;
	
	@JsonView(View.External.class)
	@GetMapping(path = "/external/findAll")
	public List<User> findAll() {
		return userService.getAllUsers();
	}
	
	@JsonView(View.Internal.class)
	@GetMapping(path = "/internal/findAll")
	public List<User> findAll2() {
		return userService.getAllUsers();
	}
	
	@JsonView(View.Internal.class)
	@GetMapping(path = "/internal/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			return userService.findUser(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@JsonView(View.External.class)
	@GetMapping(path = "/external/{id}")
	public Optional<User> getUserById2 (@PathVariable("id") @Min(1) Long id){
		try {
			return userService.findUser(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

}
