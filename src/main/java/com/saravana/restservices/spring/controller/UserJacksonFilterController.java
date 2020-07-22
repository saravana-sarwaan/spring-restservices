package com.saravana.restservices.spring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
@RequestMapping("/jacksonFilter/user")
@Validated
public class UserJacksonFilterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findAll")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id,
			@RequestParam Set<String> fields){
		try {
			Optional<User> optionalUser = userService.findUser(id);
			User user = optionalUser.get();
			/*
			 * Set<String> fields = new HashSet<String>(); fields.add("firstName");
			 * fields.add("lastName");
			 */
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
}
