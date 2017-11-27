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

	//final static Logger log = LoggerFactory.getLogger(GenreFacadeImpl.class);

	@Inject
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public Long create(GenreCreateDTO g) {
		Genre mappedGenre = beanMappingService.mapTo(g, Genre.class);
		//save genre
		Genre newGenre = genreService.create(mappedGenre);
		return newGenre.getId();
	}

	@Override
	public void delete(GenreDTO genre) {
		genreService.delete(beanMappingService.mapTo(genre, Genre.class));
	}

	@Override
	public void update(GenreDTO genre) {
		genreService.update(beanMappingService.mapTo(genre, Genre.class));
	}

	@Override
	public List<GenreDTO> findAll() {
		return beanMappingService.mapTo(genreService.findAll(), GenreDTO.class);
	}

	@Override
	public GenreDTO findById(Long id) {
		Genre genre = genreService.findById(id);
		return (genre == null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
	}

	@Override
	public List<GenreDTO> findByName(String query) {
		List<Genre> genres = genreService.findByName(query);
		return (genres == null) ? null : beanMappingService.mapTo(genres, GenreDTO.class);
	}
}
