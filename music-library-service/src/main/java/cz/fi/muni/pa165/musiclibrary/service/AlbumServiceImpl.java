package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implementation of AlbumFacade
 *
 * @author Iva Liberova
 */

@Service
public class AlbumServiceImpl implements AlbumService {

	private final AlbumDao albumDao;
	private final TimeService timeService;

	@Autowired
	public AlbumServiceImpl(AlbumDao albumDao, TimeService timeService) {
		this.albumDao = albumDao;
		this.timeService = timeService;
	}

	@Override
	public Album create(Album album) {
		albumDao.create(album);
		return album;
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
	public List<Album> findByTitle(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		return albumDao.findByTitle(patterns);
	}

	@Override
	public List<Album> findAll() {
		return albumDao.findAll();
	}

	@Override
	public List<Album> getAlbumsReleasedBetween(Date startDate, Date endDate) {
		return albumDao.getAlbumsReleasedBetween(startDate, endDate);
	}

	@Override
	public List<Album> getAlbumsFromLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeService.getCurrentTime());
		calendar.add(Calendar.MONTH, -1);
		Date lastMonth = calendar.getTime();
		return albumDao.getAlbumsReleasedBetween(lastMonth, timeService.getCurrentTime());
	}
}
