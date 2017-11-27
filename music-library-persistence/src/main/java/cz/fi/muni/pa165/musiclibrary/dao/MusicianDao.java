package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;

import java.util.List;

/**
 * @author David Koncak
 */
public interface MusicianDao {

	/**
	 * Adds the given musician to the database.
	 *
	 * @param musician the musician
	 */
	void create(Musician musician);

	/**
	 * Updates the given musician.
	 *
	 * @param musician the musician
	 */
	void update(Musician musician);

	/**
	 * Deletes the given musician.
	 *
	 * @param musician the musician
	 */
	void delete(Musician musician);

	/**
	 * Gets a musician with the given ID.
	 *
	 * @param id the musician ID
	 * @return the musician
	 */
	Musician findById(Long id);

	/**
	 * Gets all musicians with a name matching all the given patterns.
	 *
	 * @param patterns the patterns to be found
	 * @return the list of musicians
	 */
	List<Musician> findByName(List<String> patterns);

	/**
	 * Gets all musicians in the database.
	 *
	 * @return the list of musicians
	 */
	List<Musician> findAll();
}
