package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.service.AlbumService;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import cz.fi.muni.pa165.musiclibrary.service.MusicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Implementation of album facade interface.
 *
 * @author Iva Liberova
 */
@Service
@Transactional
public class AlbumFacadeImpl implements AlbumFacade {
	@Autowired
	private AlbumService albumService;

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public void create(AlbumDTO albumDTO) {
		albumService.create(beanMappingService.mapTo(albumDTO, Album.class));
	}

	@Override
	public void update(AlbumDTO albumDTO) throws IllegalArgumentException {
		albumService.update(beanMappingService.mapTo(albumDTO, Album.class));
	}

	@Override
	public void remove(AlbumDTO albumDTO) throws IllegalArgumentException {
		albumService.remove(beanMappingService.mapTo(albumDTO, Album.class));
	}

	@Override
	public AlbumDTO findById(Long id) {
		Album album = albumService.findById(id);
		return (album == null) ? null : beanMappingService.mapTo(album, AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> findByMusician(MusicianDTO musicianDTO) {
		Musician musician = musicianService.findById(musicianDTO.getId());
		return beanMappingService.mapTo(albumService.findByMusician(musician), AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> findByGenre(GenreDTO genreDTO) {
		Genre genre = genreService.findById(genreDTO.getId());
		return beanMappingService.mapTo(albumService.findByGenre(genre), AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> findByTitle(String titlePattern) {
		return beanMappingService.mapTo(albumService.findByTitle(titlePattern), AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> findAll() {
		return beanMappingService.mapTo(albumService.findAll(), AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> getAlbumsReleasedBetween(Date startDate, Date endDate) {
		return beanMappingService.mapTo(albumService.getAlbumsReleasedBetween(startDate, endDate), AlbumDTO.class);
	}

	@Override
	public List<AlbumDTO> getAlbumsFromLastMonth() {
		return beanMappingService.mapTo(albumService.getAlbumsFromLastMonth(), AlbumDTO.class);
	}
}
