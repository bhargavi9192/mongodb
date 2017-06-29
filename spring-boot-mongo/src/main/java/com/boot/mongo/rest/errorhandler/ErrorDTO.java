package com.boot.mongo.rest.errorhandler;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
public final class ErrorDTO {

	private final String field;

	private final String message;

	ErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
