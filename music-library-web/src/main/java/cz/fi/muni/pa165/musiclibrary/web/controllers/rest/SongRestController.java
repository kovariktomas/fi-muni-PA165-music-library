package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xkoncak
 */
@Controller
@RestController
@RequestMapping("/rest/songs")
public class SongRestController {

	@Autowired
	private SongFacade songFacade;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final SongDTO find(@PathVariable("id") long id) throws Exception {
		SongDTO songDTO = songFacade.findById(id);

		if (songDTO == null) {
			throw new ResourceNotFoundException();
		}

		return songDTO;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<SongDTO> findAll() {
		return songFacade.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void delete(@PathVariable("id") long id) throws Exception {
		try {
			songFacade.delete(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}

	@RequestMapping(value = "/by_musician/{musician_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<SongDTO> findByMusician(@PathVariable("musician_id") long musicianId) {
		return songFacade.findByMusician(musicianId);

	}

	@RequestMapping(value = "/by_genre/{genre_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<SongDTO> findByGenre(@PathVariable("genre_id") long genreId) {
		return songFacade.findByGenre(genreId);
	}

	@RequestMapping(value = "/by_title", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<SongDTO> findByTitle(@RequestParam("title") String title) {
		return songFacade.findByTitle(title);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final SongDTO createSong(@RequestBody SongCreateDTO song) throws Exception {
		try {
			Long id = songFacade.create(song);
			return songFacade.findById(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}
}
