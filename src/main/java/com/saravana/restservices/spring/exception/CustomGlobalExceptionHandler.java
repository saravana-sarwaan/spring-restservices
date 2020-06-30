package com.saravana.restservices.spring.exception;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "From the newly created global exception handler",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "This Http method is not supported",
				ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	/*
	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex , WebRequest web) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "The user name is not found",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	*/
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex , WebRequest web) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "please donot violate the rules",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

}
