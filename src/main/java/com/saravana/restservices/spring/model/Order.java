package com.saravana.restservices.spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order>{
	
	@Id
	@GeneratedValue
	private Long orderId;
	private String orderDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Order() {
		
	}

	public Order(Long orderId, String orderDescription, User user) {
		super();
		this.orderId = orderId;
		this.orderDescription = orderDescription;
		this.user = user;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public User getUser() {
		return user;
	}	

}
