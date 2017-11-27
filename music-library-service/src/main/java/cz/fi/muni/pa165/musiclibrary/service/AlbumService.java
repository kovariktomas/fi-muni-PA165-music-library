package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;

import java.util.List;

/**
 * @author Iva Liberova
 */
public interface AlbumService {
	void create(Album album);

	void update(Album album) throws IllegalArgumentException;

	void remove(Album album) throws IllegalArgumentException;

	Album findById(Long id);

	List<Album> findByMusician(Musician musician);

	List<Album> findByGenre(Genre genre);

	List<Album> findByTitle(String titlePattern);

	List<Album> findAll();

	List<Album> getAlbumsFromLastMonth();

	List<Album> searchAlbumsByQuery(List<String> titlePatterns);
}
