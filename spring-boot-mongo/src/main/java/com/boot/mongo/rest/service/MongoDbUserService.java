package com.boot.mongo.rest.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
@Service
final class MongoDBUserService implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBUserService.class);

	private final UserRepository repository;

	@Autowired
	MongoDBUserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		LOGGER.info("Creating a new user entry with information: {}", userDTO);

		User persisted = User.getBuilder().city(userDTO.getCity()).name(userDTO.getName()).build();

		persisted = repository.save(persisted);
		LOGGER.info("Created a new user entry with information: {}", persisted);

		return convertToDTO(persisted);
	}

	@Override
	public UserDTO delete(String id) {
		LOGGER.info("Deleting a user entry with id: {}", id);

		User deleted = findUserById(id);
		repository.delete(deleted);

		LOGGER.info("Deleted user entry with informtation: {}", deleted);

		return convertToDTO(deleted);
	}

	@Override
	public List<UserDTO> findAll() {
		LOGGER.info("Finding all user entries.");

		List<User> userEntries = repository.findAll();

		LOGGER.info("Found {} user entries", userEntries.size());

		return convertToDTOs(userEntries);
	}

	private List<UserDTO> convertToDTOs(List<User> models) {
		return models.stream().map(this::convertToDTO).collect(toList());
	}

	@Override
	public UserDTO findById(String id) {
		LOGGER.info("Finding user entry with id: {}", id);

		User found = findUserById(id);

		LOGGER.info("Found user entry: {}", found);

		return convertToDTO(found);
	}

	@Override
	public UserDTO update(UserDTO user) {
		LOGGER.info("Updating user entry with information: {}", user);

		User updated = findUserById(user.getId());
		updated.update(user.getCity(), user.getName());
		updated = repository.save(updated);

		LOGGER.info("Updated user entry with information: {}", updated);

		return convertToDTO(updated);
	}

	private User findUserById(String id) {
		Optional<User> result = repository.findOne(id);
		return result.orElseThrow(() -> new UserNotFoundException(id));

	}

	private UserDTO convertToDTO(User model) {
		UserDTO dto = new UserDTO();

		dto.setId(model.getId());
		dto.setCity(model.getCity());
		dto.setName(model.getName());

		return dto;
	}
}
