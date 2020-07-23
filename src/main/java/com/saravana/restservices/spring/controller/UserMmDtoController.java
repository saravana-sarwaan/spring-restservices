package com.saravana.restservices.spring.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.saravana.restservices.spring.dto.UserMmDto;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
@Validated
@RequestMapping(path = "/modelmap/users")
public class UserMmDtoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path = "/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			Optional<User> optionalUser = userService.findUser(id);
			User user = optionalUser.get();
			UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
			return userMmDto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

}
