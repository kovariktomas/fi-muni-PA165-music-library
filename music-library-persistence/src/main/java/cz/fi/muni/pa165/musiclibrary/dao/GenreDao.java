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
	 * @throws IllegalArgumentException    if the genre is null or already exists
	 * @throws GenreAlreadyExistsException if a genre with the same name already exists
	 */
	public void create(Genre genre) throws GenreAlreadyExistsException, IllegalArgumentException;

	/**
	 * Deletes the given genre from the database.
	 *
	 * @param genre the genre
	 * @throws IllegalArgumentException if the genre is null
	 */
	public void delete(Genre genre) throws IllegalArgumentException;

	/**
	 * Updates the given genre in the database.
	 *
	 * @param genre the genre
	 * @throws IllegalArgumentException if the genre is null or does not exist
	 */
	public void update(Genre genre) throws IllegalArgumentException;

	/**
	 * Gets a genre with the given ID.
	 *
	 * @param id the genre ID
	 * @return the genre, or null if does not exist
	 * @throws IllegalArgumentException if the id is null
	 */
	public Genre findById(Long id) throws IllegalArgumentException;

	/**
	 * Gets genres which contain the given pattern in the name.
	 *
	 * @param namePattern the name pattern
	 * @return the genres
	 * @throws IllegalArgumentException if the name pattern is null
	 */
	public List<Genre> findByName(String namePattern) throws IllegalArgumentException;

	/**
	 * Gets all genres.
	 *
	 * @return the genres
	 */
	public List<Genre> findAll();
}

