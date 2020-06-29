package com.saravana.restservices.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saravana.restservices.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String username);

}
