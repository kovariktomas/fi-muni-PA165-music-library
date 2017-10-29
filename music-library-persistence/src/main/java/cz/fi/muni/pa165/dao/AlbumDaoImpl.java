/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of DAO layer of entity Album.
 *
 * @author Iva Liberova
 */
@Repository
@Transactional
public class AlbumDaoImpl implements AlbumDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Album album) throws IllegalArgumentException {
		if (album == null) {
			throw new IllegalArgumentException("Creating album - album cannot be null.");
		}
		if (em.contains(album)) {
			throw new IllegalArgumentException("Creating album - album already in database.");
		}
		em.persist(album);
	}

	@Override
	public void update(Album album)  throws IllegalArgumentException {
		if (album == null) {
			throw new IllegalArgumentException("Updating album - album cannot be null.");
		}
		if (!em.contains(album)) {
			throw new IllegalArgumentException("Updating album - album not in database.");
		}
		em.merge(album);
	}

	@Override
	public void remove(Album album) throws IllegalArgumentException {
		if (album == null) {
			throw new IllegalArgumentException("Removing album - album cannot be null.");
		}
		if (!em.contains(album)) {
			throw new IllegalArgumentException("Removing album - album not in database.");
		}
		em.remove(findById(album.getId()));
	}

	@Override
	public Album findById(Long id) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("Finding album by id - id cannot be null.");
		}
		if (id < 0) {
			throw new IllegalArgumentException("Finding album by id - id cannot be negative number.");
		}
		return em.find(Album.class, id);
	}

	@Override
	public List<Album> findByMusician(Musician musician) throws IllegalArgumentException{
		if (musician == null) {
			throw new IllegalArgumentException("Finding album by musician - musician cannot be null.");
		}
		List<Song> songs = em.createQuery("SELECT s FROM Song s WHERE s.musician = :musician",
			Song.class).setParameter("musician", "%"+musician+"%").getResultList();
		List<Album> albums = new ArrayList<>();
		for (Song song : songs) {
			albums.add(song.getAlbum());
		}
		return albums.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Album> findByGenre(Genre genre) throws IllegalArgumentException{
		if (genre == null) {
			throw new IllegalArgumentException("Finding album by genre - genre cannot be null.");
		}
		List<Song> songs = em.createQuery("SELECT s FROM Song s WHERE s.genre = :genre",
			Song.class).setParameter("genre", "%"+genre+"%").getResultList();
		List<Album> albums = new ArrayList<>();
		for (Song song : songs) {
			albums.add(song.getAlbum());
		}
		return albums.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Album> findByTitle(String titlePattern) throws IllegalArgumentException {
		if (titlePattern == null) {
			throw new IllegalArgumentException("Finding album by title - title cannot be null.");
		}
		return em.createQuery("SELECT a FROM Album a WHERE a.title like :title ",
			Album.class).setParameter("title", "%"+titlePattern+"%").getResultList();
	}

	@Override
	public List<Album> findAll() {
		return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
	}
}
