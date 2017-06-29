package com.boot.mongo.rest.service;

import static com.boot.mongo.rest.util.PreCondition.isTrue;
import static com.boot.mongo.rest.util.PreCondition.notEmpty;
import static com.boot.mongo.rest.util.PreCondition.nullCheck;

import org.springframework.data.annotation.Id;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
final class User {

	static final int MAX_LENGTH_DESCRIPTION = 500;
	static final int MAX_LENGTH_TITLE = 100;

	@Id
	private String id;

	private String name;

	private String city;

	public User() {
	}

	private User(Builder builder) {
		this.name = builder.name;
		this.city = builder.city;
	}

	static Builder getBuilder() {
		return new Builder();
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

	public void update(String city, String name) {
		checkTitleAndDescription(city, name);

		this.city = city;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("User[id=%s, name=%s, city=%s]", this.id, this.name, this.city);
	}

	static class Builder {

		private String name;

		private String city;

		private Builder() {
		}

		Builder name(String name) {
			this.name = name;
			return this;
		}

		Builder city(String city) {
			this.city = city;
			return this;
		}

		User build() {
			User build = new User(this);

			build.checkTitleAndDescription(build.getCity(), build.getName());

			return build;
		}
	}

	private void checkTitleAndDescription(String title, String description) {
		nullCheck(title, "Title cannot be null");
		notEmpty(title, "Title cannot be empty");
		isTrue(title.length() <= MAX_LENGTH_TITLE, "Title cannot be longer than %d characters", MAX_LENGTH_TITLE);

		if (description != null) {
			isTrue(description.length() <= MAX_LENGTH_DESCRIPTION, "Description cannot be longer than %d characters",
					MAX_LENGTH_DESCRIPTION);
		}
	}
}
