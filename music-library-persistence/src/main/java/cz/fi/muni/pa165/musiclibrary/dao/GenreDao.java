package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;
import cz.fi.muni.pa165.musiclibrary.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface GenreDao {

	/**
	 * Adds the given genre to the database.
	 *
	 * @param genre the genre
	 * @throws InvalidArgumentException    if the genre is null or already exists
	 * @throws GenreAlreadyExistsException if a genre with the same name already exists
	 */
	public void create(Genre genre) throws GenreAlreadyExistsException, InvalidArgumentException;

	/**
	 * Deletes the given genre from the database.
	 *
	 * @param genre the genre
	 * @throws InvalidArgumentException if the genre is null
	 */
	public void delete(Genre genre) throws InvalidArgumentException;

	/**
	 * Updates the given genre in the database.
	 *
	 * @param genre the genre
	 * @throws InvalidArgumentException if the genre is null or does not exist
	 */
	public void update(Genre genre) throws InvalidArgumentException;

	/**
	 * Gets a genre with the given ID.
	 *
	 * @param id the genre ID
	 * @return the genre, or null if does not exist
	 * @throws InvalidArgumentException if the id is null
	 */
	public Genre findById(Long id) throws InvalidArgumentException;

	/**
	 * Gets genres which contain the given pattern in the name.
	 *
	 * @param patterns patterns to be found
	 * @return the genres
	 * @throws InvalidArgumentException if the name pattern is null
	 */
	public List<Genre> findByName(List<String> patterns) throws InvalidArgumentException;

	/**
	 * Gets all genres.
	 *
	 * @return the genres
	 */
	public List<Genre> findAll();
}

