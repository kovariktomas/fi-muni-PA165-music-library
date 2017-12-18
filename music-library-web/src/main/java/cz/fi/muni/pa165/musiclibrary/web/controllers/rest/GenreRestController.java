package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceAlreadyExistsException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for genres.
 *
 * @author Jan-Sebastian Fabik
 */
@Controller
@RestController
@RequestMapping("/rest/genres")
public class GenreRestController {

	final static Logger log = LoggerFactory.getLogger(GenreRestController.class);

	@Autowired
	private GenreFacade genreFacade;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<GenreDTO> findAll() {
		log.debug("findAll()");
		return genreFacade.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final GenreDTO find(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.debug("find({})", id);

		GenreDTO genre = genreFacade.findById(id);

		if (genre == null) {
			throw new ResourceNotFoundException();
		}

		return genre;
	}

	@RequestMapping(value = "/by_name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<GenreDTO> findByName(@RequestParam("name") String name) {
		return genreFacade.findByName(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void delete(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.debug("delete({})", id);

		if (genreFacade.findById(id) == null) {
			throw new ResourceNotFoundException();
		}

		genreFacade.delete(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final GenreDTO create(@RequestBody GenreCreateDTO genreCreate) throws ResourceAlreadyExistsException {
		log.debug("create({})", genreCreate);

		Long id;

		try {
			id = genreFacade.create(genreCreate);
		} catch (GenreAlreadyExistsException ex) {
			throw new ResourceAlreadyExistsException(ex);
		}

		return genreFacade.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final GenreDTO update(@PathVariable("id") long id, @RequestBody GenreDTO genre) throws ResourceNotFoundException {
		log.debug("update({}, {})", id, genre);
		genre.setId(id);

		if (genreFacade.findById(id) == null) {
			throw new ResourceNotFoundException();
		}

		genreFacade.update(genre);
		return genreFacade.findById(id);
	}
}
