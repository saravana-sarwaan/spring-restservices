package com.saravana.restservices.spring.helloController;


import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saravana.restservices.spring.model.UserDetails;

//RestController for Hello world
@RestController
public class HelloController {
	
	
	//Rest end point
	//Method and path
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/userData")
	public UserDetails getData() {
		return new  UserDetails("Saravanan", "Thangasamy", 27);
	}

}
