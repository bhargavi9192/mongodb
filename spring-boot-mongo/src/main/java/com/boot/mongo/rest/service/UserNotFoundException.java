package com.boot.mongo.rest.service;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String id) {
		super(String.format("No user entry found with id: <%s>", id));
	}
}
