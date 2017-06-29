package com.boot.mongo.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
interface UserRepository extends Repository<User, String> {

	/**
	 * 
	 * @param deleted
	 */
	void delete(User deleted);

	/**
	 * 
	 * @return
	 */
	List<User> findAll();

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<User> findOne(String id);

	/**
	 * 
	 * @param saved
	 * @return
	 */
	User save(User saved);
}
