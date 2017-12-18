package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for administering musicians.
 *
 * @author Tomas Kovarik
 */
@Controller
@RestController
@RequestMapping("/rest/musicians")
public class MusicianRestController {

	final static Logger log = LoggerFactory.getLogger(MusicianRestController.class);

	@Autowired
	private MusicianFacade musicianFacade;

	/**
	 * Get list of Musicians
	 * <p>
	 * curl -i -X GET http://localhost:8080/pa165/rest/musicians
	 *
	 * @return MusicianDTO
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<MusicianDTO> findAll() {
		log.debug("findAll()");
		return musicianFacade.findAll();
	}

	/**
	 * Get Musician by identifier id
	 * curl -i -X GET http://localhost:8080/pa165/rest/musicians/1
	 *
	 * @param id identifier for a musician
	 * @return MusicianDTO
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final MusicianDTO find(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.debug("find({})", id);

		MusicianDTO musician = musicianFacade.findById(id);

		if (musician == null) {
			throw new ResourceNotFoundException();
		}

		return musician;
	}

	/**
	 * Delete one musician by id
	 * curl -i -X DELETE http://localhost:8080/pa165/rest/musicians/1
	 *
	 * @param id identifier for musician
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void delete(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.debug("delete({})", id);
		try {
			musicianFacade.delete(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}

	/**
	 * Create a new musician by POST method
	 * <p>
	 * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test"}' http://localhost:8080/pa165/rest/musicians/create
	 *
	 * @param musician MusicianCreateDTO with required fields for creation
	 * @return void
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final MusicianDTO create(@RequestBody MusicianCreateDTO musician) {

		log.debug("create({})", musician);

		Long id = musicianFacade.create(musician);
		return musicianFacade.findById(id);
	}

	/**
	 * Update a musician with the given ID by PUT method
	 * curl -X PUT -i -H "Content-Type: application/json" --data '{"name":"UpdatedName"}' http://localhost:8080/pa165/rest/musicians/1
	 *
	 * @param id      identified of the musician to be updated
	 * @param musician required fields as specified in MusicianDTO
	 * @return the updated musician MusicianDTO
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final MusicianDTO update(@PathVariable("id") long id, @RequestBody MusicianDTO musician) throws ResourceNotFoundException {
		log.debug("update({}, {})", id, musician);

		if (musicianFacade.findById(id) == null) {
			throw new ResourceNotFoundException();
		}

		musician.setId(id);
		musicianFacade.update(musician);
		return musicianFacade.findById(id);
	}

	@RequestMapping(value = "/by_name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<MusicianDTO> findByName(@RequestParam("name") String name) {
		return musicianFacade.findByName(name);
	}
}
