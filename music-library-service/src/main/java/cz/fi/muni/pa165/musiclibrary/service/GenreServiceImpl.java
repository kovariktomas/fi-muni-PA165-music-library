package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Kovarik Tomas
 */

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreDao genreDao;

	@Override
	public Genre create(Genre g) {
		genreDao.create(g);
		return g;
	}

	@Override
	public void update(Genre g) {
		genreDao.update(g);
	}

	@Override
	public void delete(Genre g) {
		genreDao.delete(g);
	}

	@Override
	public Genre findById(Long id) {
		return genreDao.findById(id);
	}

	@Override
	public List<Genre> findByName(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		return genreDao.findByName(patterns);
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}
}
