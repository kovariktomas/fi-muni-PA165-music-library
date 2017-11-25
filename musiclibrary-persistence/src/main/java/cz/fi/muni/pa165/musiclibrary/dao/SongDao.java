package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;

import java.util.List;

/**
 * SongsDao provides an abstract interface to a database of songs.
 *
 * @author Jan-Sebastian Fabík
 */
public interface SongDao {

	/**
	 * Adds the given song to the database.
	 *
	 * @param song the song
	 * @throws IllegalArgumentException if the song is null or exists in database
	 */
	public void create(Song song) throws IllegalArgumentException;

	/**
	 * Updates the given song in the database.
	 *
	 * @param song the song
	 * @throws IllegalArgumentException if the song is null or does not exist in database
	 */
	public void update(Song song) throws IllegalArgumentException;

	/**
	 * Deletes the given song from the database.
	 *
	 * @param song the song
	 * @throws IllegalArgumentException if the song is null or does not exist in database
	 */
	public void delete(Song song) throws IllegalArgumentException;

	/**
	 * Gets a song with the given ID.
	 *
	 * @param id the song ID
	 * @return the song, or null if does not exist
	 * @throws IllegalArgumentException if the id is null
	 */
	public Song findById(Long id) throws IllegalArgumentException;

	/**
	 * Gets songs with the given musician.
	 *
	 * @param musician the musician
	 * @return the songs
	 * @throws IllegalArgumentException if the musician is null
	 */
	public List<Song> findByMusician(Musician musician) throws IllegalArgumentException;

	/**
	 * Gets songs with the given genre.
	 *
	 * @param genre the genre
	 * @return the songs
	 * @throws  IllegalArgumentException if the genre is null
	 */
	public List<Song> findByGenre(Genre genre) throws IllegalArgumentException;

	/**
	 * Gets songs which contain the given pattern in the title.
	 *
	 * @param titlePattern the title pattern
	 * @return the songs
	 * @throws IllegalArgumentException if the title pattern is null
	 */
	public List<Song> findByTitle(String titlePattern) throws IllegalArgumentException;

	/**
	 * Gets all songs.
	 *
	 * @return the songs
	 */
	public List<Song> findAll();
}

