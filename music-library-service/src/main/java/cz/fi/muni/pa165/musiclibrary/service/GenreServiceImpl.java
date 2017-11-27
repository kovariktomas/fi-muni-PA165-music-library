package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


/**
 * @author Kovarik Tomas
 */

@Service
public class GenreServiceImpl implements GenreService {

	@Inject
	private GenreDao genreDao;

	@Override
	public Genre findById(Long id) {
		return genreDao.findById(id);
	}
	
	@Override
	public List<Genre> findByPattern(String pattern) {
		return genreDao.findByName(pattern);
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}

	@Override
	public Genre createGenre(Genre g) {
		genreDao.create(g);
		return g;
	}

	@Override
	public void deleteGenre(Genre g) {
		genreDao.delete(g);
	}
}