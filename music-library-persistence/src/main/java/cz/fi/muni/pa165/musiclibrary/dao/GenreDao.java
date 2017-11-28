package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface GenreDao {

	/**
	 * Adds the given genre to the database.
	 *
	 * @param genre the genre
	 * @throws GenreAlreadyExistsException if a genre with the same name already exists
	 */
	void create(Genre genre) throws GenreAlreadyExistsException;

	/**
	 * Deletes the given genre from the database.
	 *
	 * @param genre the genre
	 */
	void delete(Genre genre);

	/**
	 * Updates the given genre in the database.
	 *
	 * @param genre the genre
	 */
	void update(Genre genre);

	/**
	 * Gets a genre with the given ID.
	 *
	 * @param id the genre ID
	 * @return the genre, or null if does not exist
	 */
	Genre findById(Long id);

	/**
	 * Gets genres which contain the given pattern in the name.
	 *
	 * @param patterns patterns to be found
	 * @return the genres
	 */
	List<Genre> findByName(List<String> patterns);

	/**
	 * Gets all genres.
	 *
	 * @return the genres
	 */
	List<Genre> findAll();
}

