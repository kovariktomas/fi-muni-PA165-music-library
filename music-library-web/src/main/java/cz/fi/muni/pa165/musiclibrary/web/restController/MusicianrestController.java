package cz.fi.muni.pa165.musiclibrary.web.restController;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.GenreCreateDTOValidator;
import cz.fi.muni.pa165.musiclibrary.exceptions.MusicLibraryServiceException;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import org.springframework.http.MediaType;

/**
 * SpringMVC Controller for administering genres.
 *
 * @author Tomas Kovarik
 */
@Controller
@RestController
@RequestMapping("/musicianrest")
public class MusicianrestController {
	
	final static Logger log = LoggerFactory.getLogger(MusicianrestController.class);

    @Autowired
    private MusicianFacade musicianFacade;
	
   
	/**
     * Get list of Musicians 
	 * 
	 * curl -i -X GET http://localhost:8080/music-library-web/musicianrest
     *
     * @return MusicianDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MusicianDTO> getMusicians() {
        log.debug("rest getMusicians()");
        return musicianFacade.findAll();
    }
    /**
     *
     * Get Musician by identifier id 
	 * curl -i -X GET http://localhost:8080/music-library-web/musicianrest/1
     * @param id identifier for a musician
     * @return MusicianDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MusicianDTO getMusician(@PathVariable("id") long id) throws Exception {
        log.debug("rest getMusician({})", id);

        try {
            MusicianDTO musicianDTO = musicianFacade.findById(id);
            return musicianDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one musician by id 
	 * curl -i -X DELETE http://localhost:8080/music-library-web/musicianrest/1
     *
     * @param id identifier for musician
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMusician(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteMusician({})", id);
        try {
            musicianFacade.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new musician by POST method
     * 
	 * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test"}' http://localhost:8080/music-library-web/musicianrest/create
     * 
     * @param musician MusicianCreateDTO with required fields for creation
     * @return void
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void /*MusicianDTO*/ createGenre(@RequestBody MusicianCreateDTO musician) throws Exception {

        log.debug("rest createMusician()");

        try {
            //Long id = musicianFacade.create(musician);
            //return musicianFacade.findById(id);
			musicianFacade.create(musician);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Update the name for one musician by PUT method 
	 * curl -X PUT -i -H "Content-Type: application/json" --data '{"name":"UpdatedName"}' http://localhost:8080/music-library-web/musicianrest/1
     *
     * @param id identified of the musician to be updated
     * @param newName required fields as specified in MusicianCreateDTO
     * @return the updated musician MusicianDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MusicianDTO changeName(@PathVariable("id") long id, @RequestBody MusicianCreateDTO newName) throws Exception {

        log.debug("rest changeName({})", id);

        try {
			MusicianDTO musician = musicianFacade.findById(id);
            musician.setName(newName.getName());
			musicianFacade.update(musician);
            return musicianFacade.findById(id);
        } catch (MusicLibraryServiceException esse) {
            throw new InvalidParameterException();
        }

    }
}