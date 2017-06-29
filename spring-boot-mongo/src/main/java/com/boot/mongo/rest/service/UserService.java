package com.boot.mongo.rest.service;

import java.util.List;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
interface UserService {

	/**
	 * 
	 * @param user
	 * @return
	 */
	UserDTO create(UserDTO user);

	/**
	 * 
	 * @param id
	 * @return
	 */
	UserDTO delete(String id);

	/**
	 * 
	 * @return
	 */
	List<UserDTO> findAll();

	/**
	 * 
	 * @param id
	 * @return
	 */
	UserDTO findById(String id);

	/**
	 * 
	 * @param user
	 * @return
	 */
	UserDTO update(UserDTO user);
}
