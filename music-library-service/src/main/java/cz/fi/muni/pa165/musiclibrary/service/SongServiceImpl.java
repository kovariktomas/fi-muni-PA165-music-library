package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.SongDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David
 */
@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongDao songDao;

	@Override
	public Song findById(Long id) {
		return songDao.findById(id);
	}

	@Override
	public void create(Song song) {
		songDao.create(song);
	}

	@Override
	public void remove(Song song) {
		songDao.delete(song);
	}

	@Override
	public List<Song> findAll() {
		return songDao.findAll();
	}

	@Override
	public List<Song> findByMusician(Musician musician) {
		return songDao.findByMusician(musician);
	}

	@Override
	public List<Song> findByGenre(Genre genre) {
		return songDao.findByGenre(genre);
	}

	@Override
	public List<Song> findByTitle(String titlePattern) {
		return songDao.findByTitle(titlePattern);
	}

}
