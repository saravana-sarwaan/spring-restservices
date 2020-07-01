package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.saravana.restservices.spring.exception.OrderExistsException;
import com.saravana.restservices.spring.model.Order;
import com.saravana.restservices.spring.repository.OrderRepository;
import com.saravana.restservices.spring.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping(path = "/orders/findAll")
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
	
	
	@GetMapping(path = "/orders/{id}")
	public Optional<Order> getOrderById(@PathVariable("id") Long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order order) {
		
		try {
			return orderService.createOrder(order);
		} catch (OrderExistsException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping(path = "/orders/findByUser/{id}")
	public List<Order> findByUserId(@PathVariable("id") Long id){
		return orderService.findByUserId(id);
	}

}
