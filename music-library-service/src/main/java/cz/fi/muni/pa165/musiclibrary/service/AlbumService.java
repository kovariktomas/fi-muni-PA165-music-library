package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Interface of Album service layer.
 *
 * @author Iva Liberova
 */
@Service
public interface AlbumService {
	/**
	 * Creates new album
	 *
	 * @param album that will be created
	 */
	void create(Album album);

	/**
	 * Updates existing album
	 *
	 * @param album that will be modified
	 */
	void update(Album album) throws IllegalArgumentException;

	/**
	 * Removes given album
	 *
	 * @param album that will be removed
	 */
	void remove(Album album) throws IllegalArgumentException;

	/**
	 * Finds album with given id
	 *
	 * @param id to be found
	 * @return album with given id
	 */
	Album findById(Long id);

	/**
	 * Returns all albums that contain song of given musician
	 *
	 * @param musician author of a song
	 * @return list of albums that contain song of given musician
	 */
	List<Album> findByMusician(Musician musician);

	/**
	 * Returns all albums that contain some song of given genre
	 *
	 * @param genre of some song in album
	 * @return list of albums that contain songs of given genre
	 */
	List<Album> findByGenre(Genre genre);

	/**
	 * Returns all albums with a title matching the given search query
	 *
	 * @param query the search query
	 * @return list of albums containing given pattern
	 */
	List<Album> findByTitle(String query);

	/**
	 * Returns all albums
	 *
	 * @return list of all albums
	 */
	List<Album> findAll();

	/**
	 * Returns all albums released between given dates
	 *
	 * @param startDate first valid date
	 * @param endDate   last valid date
	 * @return list of all albums that were released
	 */
	List<Album> getAlbumsReleasedBetween(Date startDate, Date endDate);

	/**
	 * Returns all albums released in last month
	 *
	 * @return list of all albums that were released
	 */
	List<Album> getAlbumsFromLastMonth();
}
