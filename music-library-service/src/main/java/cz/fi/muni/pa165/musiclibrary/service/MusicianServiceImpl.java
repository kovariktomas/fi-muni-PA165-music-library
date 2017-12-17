package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.MusicianDao;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@Service
public class MusicianServiceImpl implements MusicianService {

	private final MusicianDao musicianDao;

	@Autowired
	public MusicianServiceImpl(MusicianDao musicianDao) {
		this.musicianDao = musicianDao;
	}

	public Musician create(Musician musician) {
		musicianDao.create(musician);
		return musician;
	}

	public void update(Musician musician) {
		musicianDao.update(musician);
	}

	public void delete(Musician musician) {
		musicianDao.delete(musician);
	}

	public Musician findById(Long id) {
		return musicianDao.findById(id);
	}

	public List<Musician> findByName(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		return musicianDao.findByName(patterns);
	}

	public List<Musician> findAll() {
		return musicianDao.findAll();
	}
}
