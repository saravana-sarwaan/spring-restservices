package com.saravana.restservices.spring.dto;

public class UserMmDto {
	
	private Long id;
	private String userName;
	private String firstName;
	
	
	private String emailAddress;
	
	public UserMmDto() {
	}

	public UserMmDto(Long id, String userName, String firstName, String emailAddress) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
