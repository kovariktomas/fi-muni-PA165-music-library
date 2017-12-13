package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import cz.fi.muni.pa165.musiclibrary.exceptions.UserAlreadyExistsException;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface ApplicationUserDao {

	/**
	 * Adds the given user to the database.
	 *
	 * @param user the user
	 * @throws UserAlreadyExistsException if a user with the same e-mail already exists
	 */
	void create(ApplicationUser user) throws UserAlreadyExistsException;

	/**
	 * Deletes the given user from the database.
	 *
	 * @param user the user
	 */
	void delete(ApplicationUser user);

	/**
	 * Updates the given user in the database.
	 *
	 * @param user the user
	 */
	void update(ApplicationUser user);

	/**
	 * Gets a user with the given ID.
	 *
	 * @param id the user ID
	 * @return the user, or null if does not exist
	 */
	ApplicationUser findById(Long id);

	/**
	 * Gets a user with the given email.
	 *
	 * @param email the user ID
	 * @return the user, or null if does not exist
	 */
	ApplicationUser findByEmail(String email);


	/**
	 * Gets all users.
	 *
	 * @return the users
	 */
	List<ApplicationUser> findAll();
}

