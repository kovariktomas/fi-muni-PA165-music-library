package cz.fi.muni.pa165.musiclibrary.service.facade;


import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
@Transactional
public class GenreFacadeImpl implements GenreFacade {

	final static Logger log = LoggerFactory.getLogger(GenreFacadeImpl.class);

	@Inject
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public Long createGenre(GenreCreateDTO g) {
		Genre mappedGenre = beanMappingService.mapTo(g, Genre.class);
		//save genre
		Genre newGenre = genreService.createGenre(mappedGenre);
		return newGenre.getId();
	}

	@Override
	public void deleteGenre(Long genreId) {
		genreService.deleteGenre(new Genre(genreId));
	}

	@Override
	public List<GenreDTO> getAllGenres() {
		return beanMappingService.mapTo(genreService.findAll(), GenreDTO.class);
	}

	@Override
	public GenreDTO getGenreWithId(Long id) {
		Genre genre = genreService.findById(id);
		return (genre == null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
	}

	@Override
	public List<GenreDTO> findGenreWithPattern(String pattern) {
		List<Genre> genre = genreService.findByPattern(pattern);
		return (genre == null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
	}

}
