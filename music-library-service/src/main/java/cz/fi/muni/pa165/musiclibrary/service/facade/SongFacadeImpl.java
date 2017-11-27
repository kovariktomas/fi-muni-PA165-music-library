package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import cz.fi.muni.pa165.musiclibrary.service.MusicianService;
import cz.fi.muni.pa165.musiclibrary.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author David
 */
@Service
@Transactional
public class SongFacadeImpl implements SongFacade {

	@Autowired
	private SongService songService;

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	public void create(SongDTO songDTO) {
		Song song = beanMappingService.mapTo(songDTO, Song.class);
		songService.create(song);
	}

	public void delete(SongDTO song) {
		songService.remove(beanMappingService.mapTo(song, Song.class));
	}

	public SongDTO findById(Long id) {
		Song song = songService.findById(id);
		return (song == null) ? null : beanMappingService.mapTo(song, SongDTO.class);
	}

	public List<SongDTO> findByMusician(MusicianDTO musician) {
		Musician m = musicianService.findById(musician.getId());
		return beanMappingService.mapTo(songService.findByMusician(m), SongDTO.class);
	}

	public List<SongDTO> findByGenre(GenreDTO genre) {
		Genre g = genreService.findById(genre.getId());
		return beanMappingService.mapTo(songService.findByGenre(g), SongDTO.class);
	}

	public List<SongDTO> findByTitle(String titlePattern) {
		return beanMappingService.mapTo(songService.findByTitle(titlePattern), SongDTO.class);
	}

	public List<SongDTO> findAll() {
		return beanMappingService.mapTo(songService.findAll(), SongDTO.class);
	}
}
