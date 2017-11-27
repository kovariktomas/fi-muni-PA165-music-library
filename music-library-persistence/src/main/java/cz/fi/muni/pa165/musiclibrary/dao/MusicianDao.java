package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;

import java.util.List;

/**
 * @author David Koncak
 */
public interface MusicianDao {

	/**
	 * Create new musician in database.
	 *
	 * @param m added musician
	 */
	public void create(Musician m);

	/**
	 * Deletes the given musician.
	 *
	 * @param m the musician
	 * @throws IllegalArgumentException if the musician is null
	 */
	public void remove(Musician m) throws IllegalArgumentException;

	/**
	 * Updates given musician.
	 *
	 * @param m that will be modified
	 */
	public void update(Musician m);

	/**
	 * Gets a musician with given id.
	 *
	 * @param id the musician id
	 * @return specific musician
	 */
	public Musician findById(Long id);

	/**
	 * Returns all musicians with a name matching all the given patterns.
	 *
	 * @param patterns patterns to be found
	 * @return list of musicians
	 */
	public List<Musician> findByName(List<String> patterns);

	/**
	 * Returns all musicians in the database
	 *
	 * @return list of musicians
	 */
	public List<Musician> findAll();
}
