package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.AplicationUser;
import cz.fi.muni.pa165.musiclibrary.exceptions.UserAlreadyExistsException;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface AplicationUserDao {

	/**
	 * Adds the given user to the database.
	 *
	 * @param user the user
	 * @throws UserAlreadyExistsException if a user with the same e-mail already exists
	 */
	void create(AplicationUser user) throws UserAlreadyExistsException;

	/**
	 * Deletes the given user from the database.
	 *
	 * @param user the user
	 */
	void delete(AplicationUser user);

	/**
	 * Updates the given user in the database.
	 *
	 * @param user the user
	 */
	void update(AplicationUser user);

	/**
	 * Gets a user with the given ID.
	 *
	 * @param id the user ID
	 * @return the user, or null if does not exist
	 */
	AplicationUser findById(Long id);

	/**
	 * Gets a user with the given email.
	 *
	 * @param email the user ID
	 * @return the user, or null if does not exist
	 */
	AplicationUser findByEmail(String email);


	/**
	 * Gets all users.
	 *
	 * @return the users
	 */
	List<AplicationUser> findAll();
}

