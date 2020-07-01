package com.saravana.restservices.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saravana.restservices.spring.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByUserId(Long id);

}
