package com.saravana.restservices.spring.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomGlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex , WebRequest web) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "The user name is not found",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
}
