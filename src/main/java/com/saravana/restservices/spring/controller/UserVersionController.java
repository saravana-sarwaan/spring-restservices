package com.saravana.restservices.spring.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.saravana.restservices.spring.dto.UserV1dto;
import com.saravana.restservices.spring.dto.UserV2dto;
import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
@RequestMapping("/version/user")
public class UserVersionController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path = {"/v1.0/{id}","v1.1/{id}"})
	public UserV1dto getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			System.out.println("printing id ------>>>>> " +id);
			Optional<User> optionalUser = userService.findUser(id);
			User user = optionalUser.get();
			UserV1dto userdto = modelMapper.map(user, UserV1dto.class);
			return userdto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@GetMapping("/v2.0/{id}")
	public UserV2dto getUserById2(@PathVariable("id")  @Min(1) Long id) {
		try {
			Optional<User> optionalUser = userService.findUser(id);
			User user = optionalUser.get();
			UserV2dto userdto = modelMapper.map(user, UserV2dto.class);
			return userdto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/requestParam/{id}")
	public UserV2dto getUserById3(@RequestParam()  @Min(1) Long id) {
		try {
			Optional<User> optionalUser = userService.findUser(id);
			User user = optionalUser.get();
			UserV2dto userdto = modelMapper.map(user, UserV2dto.class);
			return userdto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
