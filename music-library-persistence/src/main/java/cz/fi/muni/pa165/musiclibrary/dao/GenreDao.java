package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface GenreDao {

	/**
	 * Adds the given genre to the database.
	 *
	 * @param genre the genre
	 */
	public void create(Genre genre);

	/**
	 * Deletes the given genre from the database.
	 *
	 * @param genre the genre
	 */
	public void delete(Genre genre) throws IllegalArgumentException;

	/**
	 * Updates the given genre in the database.
	 *
	 * @param genre the genre
	 */
	public void update(Genre genre) throws IllegalArgumentException;

	/**
	 * Gets a genre with the given ID.
	 *
	 * @param id the genre ID
	 * @return the genre, or null if does not exist
	 */
	public Genre findById(Long id);

	/**
	 * Gets genres which contain the given pattern in the name.
	 *
	 * @param namePattern the name pattern
	 * @return the genres
	 */
	public List<Genre> findByName(String namePattern);

	/**
	 * Gets all genres.
	 *
	 * @return the genres
	 */
	public List<Genre> findAll();
}

