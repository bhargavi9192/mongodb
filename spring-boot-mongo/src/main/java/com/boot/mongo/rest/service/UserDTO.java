package com.boot.mongo.rest.service;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
public final class UserDTO {

	private String id;

	@Size(max = User.MAX_LENGTH_DESCRIPTION)
	private String name;

	@NotEmpty
	@Size(max = User.MAX_LENGTH_TITLE)
	private String city;

	public UserDTO() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("UserDTO[id=%s, name=%s, city=%s]", this.id, this.name, this.city);
	}
}
