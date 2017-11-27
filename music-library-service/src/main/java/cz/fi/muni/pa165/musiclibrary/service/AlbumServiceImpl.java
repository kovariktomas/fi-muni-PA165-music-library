package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Iva Liberova
 */
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumDao;

	@Override
	public void create(Album album) {
		albumDao.create(album);
	}

	@Override
	public void update(Album album) throws IllegalArgumentException {
		albumDao.update(album);
	}

	@Override
	public void remove(Album album) throws IllegalArgumentException {
		albumDao.remove(album);
	}

	@Override
	public Album findById(Long id) {
		return albumDao.findById(id);
	}

	@Override
	public List<Album> findByMusician(Musician musician) {
		return albumDao.findByMusician(musician);
	}

	@Override
	public List<Album> findByGenre(Genre genre) {
		return albumDao.findByGenre(genre);
	}

	@Override
	public List<Album> findByTitle(String titlePattern) {
		return albumDao.findByTitle(titlePattern);
	}

	@Override
	public List<Album> findAll() {
		return albumDao.findAll();
	}

	@Override
	public List<Album> getAlbumsFromLastMonth() {
		// TODO
		return null;
	}

	@Override
	public List<Album> searchAlbumsByQuery(List<String> titlePatterns) {
		// TODO
		return null;
	}
}
