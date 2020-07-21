package com.saravana.restservices.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saravana.restservices.spring.exception.OrderExistsException;
import com.saravana.restservices.spring.model.Order;
import com.saravana.restservices.spring.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
	
	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}
	
	public Order createOrder(Order order) throws OrderExistsException{
		if(orderRepository.findById(order.getOrderId()).isPresent())
			throw new OrderExistsException("This order already exists");
		return orderRepository.save(order);
	}
	
	public List<Order> findByUserId(Long userId) {
		return orderRepository.findByUserId(userId);
	}

}
