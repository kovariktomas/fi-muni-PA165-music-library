/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
//import cz.fi.muni.pa165.entity.Genre;
//import cz.fi.muni.pa165.entity.Musician;
import java.util.List;

/**
 * Interface for DAO layer of entity Album.
 *
 * @author Iva Liberova
 */
public interface AlbumDao {
	/**
	 * Create new album in database.
	 * @param album that will be created
	 */
	public void create(Album album);

	/**
	 * Update album in database.
	 * @param album that will be modified
	 */
	public void update(Album album) throws IllegalArgumentException;

	/**
	 * Remove given album from database.
	 * @param album that will be removed
	 */
	public void remove(Album album) throws IllegalArgumentException;

	/**
	 * Finds album in database with given id.
	 * @param id to be found
	 * @return album with given id
	 */
	public Album findById(Long id);

//	/**
//	 * Returns all albums that contain song of given musician
//	 * @param musician author of a song
//	 * @return list of albums that contain song of given musician
//	 */
//	public List<Album> findByMusician(Musician musician);
//
//	/**
//	 * Returns all albums that contain some song of given genre.
//	 * @param genre of some song in album
//	 * @return list of albums that containg songs of given genre
//	 */
//	public List<Album> findByGenre(Genre genre);

	/**
	 * Returns all albums that contain given patter in title.
	 * @param titlePattern patter to be found
	 * @return list of albums containing given pattern.
	 */
	public List<Album> findByTitle(String titlePattern);

	/**
	 * Returns all albums in database.
	 * @return list of all albums
	 */
	public List<Album> findAll();
}
