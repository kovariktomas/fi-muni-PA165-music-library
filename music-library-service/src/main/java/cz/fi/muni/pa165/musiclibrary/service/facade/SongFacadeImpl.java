package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.service.*;
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
	private AlbumService albumService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public void create(SongCreateDTO songCreateDTO) {
		Song song = beanMappingService.mapTo(songCreateDTO, Song.class);
		song.setMusician(musicianService.findById(songCreateDTO.getMusicianId()));
		song.setAlbum(albumService.findById(songCreateDTO.getAlbumId()));
		song.setGenre(genreService.findById(songCreateDTO.getGenreId()));
		songService.create(song);
	}

	@Override
	public void delete(Long id) {
		songService.remove(songService.findById(id));
	}

	@Override
	public SongDTO findById(Long id) {
		Song song = songService.findById(id);
		return (song == null) ? null : beanMappingService.mapTo(song, SongDTO.class);
	}

	@Override
	public List<SongDTO> findByMusician(MusicianDTO musician) {
		Musician m = musicianService.findById(musician.getId());
		return beanMappingService.mapTo(songService.findByMusician(m), SongDTO.class);
	}

	@Override
	public List<SongDTO> findByGenre(GenreDTO genre) {
		Genre g = genreService.findById(genre.getId());
		return beanMappingService.mapTo(songService.findByGenre(g), SongDTO.class);
	}

	@Override
	public List<SongDTO> findByTitle(String query) {
		return beanMappingService.mapTo(songService.findByTitle(query), SongDTO.class);
	}

	@Override
	public List<SongDTO> findAll() {
		return beanMappingService.mapTo(songService.findAll(), SongDTO.class);
	}
}
