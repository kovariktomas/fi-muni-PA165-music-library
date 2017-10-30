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
	 * @param g the genre
	 */
	public void create(Genre g);

	/**
	 * Deletes the given genre in the database.
	 *
	 * @param g the genre
	 */
	public void delete(Genre s) throws IllegalArgumentException;

	/**
	 * Updates the given genre in the database.
	 *
	 * @param g the genre
	 */
	public void update(Genre s) throws IllegalArgumentException;

	/**
	 * Gets a genre with the given ID.
	 *
	 * @param id the genre ID
	 * @return the genre, or null if does not exist
	 */
	public Genre findById(Long id);

	/**
	 * Gets a genre which contain the given pattern in the title.
	 *
	 * @param namePattern the name pattern
	 * @return the genre, or null if does not exist
	 */
	public List<Genre> findByName(String namePattern);

	/**
	 * Gets all genres.
	 *
	 * @return the genres
	 */
	public List<Genre> findAll();
}

