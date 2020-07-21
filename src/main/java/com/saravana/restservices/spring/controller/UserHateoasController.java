package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.saravana.restservices.spring.exception.UserNotFoundException;
import com.saravana.restservices.spring.model.Order;
import com.saravana.restservices.spring.model.User;
import com.saravana.restservices.spring.service.UserService;

@RestController
@RequestMapping(value = "/hateoas/user")
public class UserHateoasController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path = "/findAll")
	public CollectionModel<User> findAll() {
		List<User> users = userService.getAllUsers();
		for (User user : users) {
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			EntityModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getOrderById(userId);
			Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderLink);
		}
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash("findAll").withSelfRel();
		CollectionModel<User> finalUser = CollectionModel.of(users,selfLink);
		return finalUser;
	}
	
	@GetMapping(path = "/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			Optional<User> userOptional = userService.findUser(id);
			User user = userOptional.get();
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalUser = EntityModel.of(user);
			return finalUser;
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

}
