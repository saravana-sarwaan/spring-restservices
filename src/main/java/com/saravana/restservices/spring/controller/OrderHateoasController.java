package com.saravana.restservices.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saravana.restservices.spring.model.Order;
import com.saravana.restservices.spring.service.OrderService;

@RestController
@RequestMapping(value = "/hateoas/order")
public class OrderHateoasController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path = "/{id}")
	public EntityModel<Order> getOrderById(@PathVariable("id") Long id) {
		Optional<Order> orderOptional = orderService.getOrderById(id);
		Order order = orderOptional.get();
		Long orderId = order.getOrderId();
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(orderId).withSelfRel();
		order.add(selfLink);
		EntityModel<Order> finalOrder = EntityModel.of(order);
		return finalOrder;
	}
	
	@GetMapping(path = "/orders/findAll")
	public CollectionModel<Order> getOrders() {
		List<Order> orders = orderService.getOrders();
		for (Order order : orders) {
			Long orderId = order.getOrderId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(orderId).withSelfRel();
			order.add(selfLink);
		}
		CollectionModel<Order> finalOrder = CollectionModel.of(orders);
		return finalOrder;
	}
	
}
