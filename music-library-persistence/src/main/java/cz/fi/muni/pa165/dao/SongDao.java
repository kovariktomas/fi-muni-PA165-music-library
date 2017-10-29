package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;

import java.util.List;

/**
 * SongsDao provides an abstract interface to a database of songs.
 *
 * @author Jan-Sebastian Fabík
 */
public interface SongDao {

	/**
	 * Adds the given song to the database.
	 * @param song the song
	 */
	public void create(Song song);

	/**
	 * Updates the given song in the database.
	 * @param song the song
	 */
	public void update(Song song);

	/**
	 * Deletes the given song from the database.
	 * @param song the song
	 */
	public void delete(Song song);

	/**
	 * Gets a song with the given ID.
	 * @param id the song ID
	 * @return the song, or null if does not exist
	 */
	public Song findById(Long id);

	/**
	 * Gets songs with the given musician.
	 * @param musician the musician
	 * @return the songs
	 */
	public List<Song> findByMusician(Musician musician);

	/**
	 * Gets songs with the given genre.
	 * @param genre the genre
	 * @return the songs
	 */
	public List<Song> findByGenre(Genre genre);

	/**
	 * Gets songs which contain the given pattern in the title.
	 * @param titlePattern the title pattern
	 * @return the songs
	 */
	public List<Song> findByTitle(String titlePattern);

	/**
	 * Gets all songs.
	 * @return the songs
	 */
	public List<Song> findAll();
}

