package com.saravana.restservices.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"userName","firstName"}) // use jsonIgnoreProperties when you want to exclude multiple fields
//@JsonFilter("userFilter") // use this for using jsonfilter -- refer jsonController
public class User extends RepresentationModel<User>{

	@Id
	@GeneratedValue
	@JsonView(View.External.class)
	private Long id;

	@JsonView(View.External.class)
	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 2, max = 10, message = "Username should be min of 2 characters and max of 10 characters")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;

	@JsonView(View.External.class)
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;
	
	@JsonView(View.External.class)
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;
	
	@JsonView(View.External.class)
	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false, unique = true)
	private String email;
	
	@JsonView(View.Internal.class)
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	
	
	//@JsonIgnore  // use json ignore to ignore selected fields
	@JsonView(View.Internal.class)
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;

	@JsonView(View.Internal.class)
	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	public User() {

	}

	public User(Long id, String userName, String firstName, String lastName, String email, String role, String ssn) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrder(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
