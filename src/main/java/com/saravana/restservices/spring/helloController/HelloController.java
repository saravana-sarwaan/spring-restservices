package com.saravana.restservices.spring.helloController;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saravana.restservices.spring.model.User;

//RestController for Hello world
@RestController
public class HelloController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	
	//Rest end point
	//Method and path
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/userData")
	public User getData() {
		return new User(501L,"kentuckey","sarwaan007", "saravanan", "thangasamy", "saravana.thangasamy@gmail.com", "admin", "123adf",null);
	}
	
	@GetMapping("/Hello-int")
	public String getMessage(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello",null, new Locale(locale));
	}

}
