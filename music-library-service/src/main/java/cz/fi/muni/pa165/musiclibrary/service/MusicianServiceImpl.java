package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.MusicianDao;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
public class MusicianServiceImpl implements MusicianService {

	@Autowired
	private MusicianDao musicianDao;

	public void create(Musician musician) {
		musicianDao.create(musician);
	}

	public void update(Musician musician) {
		musicianDao.update(musician);
	}

	public void delete(Musician musician) {
		musicianDao.remove(musician);
	}

	public Musician findById(Long id) {
		return musicianDao.findById(id);
	}

	public List<Musician> findByName(List<String> patterns) {
		return musicianDao.findByName(patterns);
	}

	public List<Musician> findAll() {
		return musicianDao.findAll();
	}
}
