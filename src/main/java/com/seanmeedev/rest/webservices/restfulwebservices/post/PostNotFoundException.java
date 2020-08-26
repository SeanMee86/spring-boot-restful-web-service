package com.seanmeedev.rest.webservices.restfulwebservices.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4464682391377908486L;

	public PostNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
