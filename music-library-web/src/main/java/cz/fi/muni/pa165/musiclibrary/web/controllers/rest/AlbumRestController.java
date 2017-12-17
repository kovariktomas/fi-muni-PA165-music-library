package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.exceptions.MusicLibraryServiceException;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for administering albums.
 *
 * @author Iva Liberova
 */
@Controller
@RestController
@RequestMapping("/albumrest")
public class AlbumRestController {

    final static Logger log = LoggerFactory.getLogger(AlbumRestController.class);

    @Autowired
    private AlbumFacade albumFacade;


    /**
     * Get list of Albums
     * curl -i -X GET http://localhost:8080/music-library-web/albumrest
     *
     * @return AlbumDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AlbumDTO> getAlbums() {
        log.debug("rest getAlbums()");
        return albumFacade.findAll();
    }

    /**
     * Get Album by identifier id
     * curl -i -X GET http://localhost:8080/music-library-web/albumrest/1
     *
     * @param id identifier for a album
     * @return AlbumDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDTO getAlbum(@PathVariable("id") long id) throws Exception {
        log.debug("rest getAlbum({})", id);

        try {
            AlbumDTO albumDTO = albumFacade.findById(id);
            return albumDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete one album by id
     * curl -i -X DELETE http://localhost:8080/music-library-web/albumrest/1
     *
     * @param id identifier for album
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteAlbum(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteAlbum({})", id);
        try {
            albumFacade.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new album by POST method
     * <p>
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test"}' http://localhost:8080/music-library-web/albumrest/create
     *
     * @param album AlbumCreateDTO with required fields for creation
     * @return void
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDTO createAlbum(@RequestBody AlbumCreateDTO album) throws Exception {

        log.debug("rest createAlbum()");

        try {
            Long id = albumFacade.create(album);
            return albumFacade.findById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Update the name for one album by PUT method
     * curl -X PUT -i -H "Content-Type: application/json" --data '{"name":"UpdatedName"}' http://localhost:8080/music-library-web/albumrest/1
     *
     * @param id      identified of the album to be updated
     * @param newAlbum required fields as specified in AlbumCreateDTO
     * @return the updated album AlbumDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDTO changeTitle(@PathVariable("id") long id, @RequestBody AlbumCreateDTO newAlbum) throws Exception {

        log.debug("rest changeTitle({})", id);

        try {
            AlbumDTO album = albumFacade.findById(id);
            album.setTitle(newAlbum.getTitle());
            albumFacade.update(album);
            return albumFacade.findById(id);
        } catch (MusicLibraryServiceException esse) {
            throw new InvalidParameterException();
        }
    }
}
