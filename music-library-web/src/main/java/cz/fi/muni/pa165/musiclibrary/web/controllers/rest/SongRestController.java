
package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xkoncak
 */
@Controller
@RestController
@RequestMapping("/rest/songs")
public class SongRestController {
    
    @Autowired
    private SongFacade songFacade;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSong(@PathVariable("id") long id) throws Exception {
       

        SongDTO songDTO = songFacade.findById(id);
        if (songDTO == null) {
            throw new ResourceNotFoundException();
        }

        return songDTO;
    
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getSongs() {
        return songFacade.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteSong(@PathVariable("id") long id) throws Exception {
        try {
            songFacade.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * 
     * @param musicianId
     * @return 
     */
    @RequestMapping(value = "by_musician/{musician_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getSongsByMusician(@PathVariable("musician_id") long musicianId) {

        List<SongDTO> songDTOs = songFacade.findByMusician(musicianId);
        if (songDTOs == null){
                throw new ResourceNotFoundException();
        }       
        return songDTOs;

    }
    
    /**
     * 
     * @param genreId
     * @return 
     */
    @RequestMapping(value = "by_genre/{genre_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getSongsByGenre(@PathVariable("genre_id") long genreId) {

        List<SongDTO> songDTOs = songFacade.findByGenre(genreId);
        if (songDTOs == null){
                throw new ResourceNotFoundException();
        }       
        return songDTOs;

    }
    
    /**
     * 
     * @param title
     * @return 
     */
    @RequestMapping(value = "by_title/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getSongsByTitle(@PathVariable("title") String title) {

        List<SongDTO> songDTOs = songFacade.findByTitle(title);
        if (songDTOs == null){
                throw new ResourceNotFoundException();
        }       
        return songDTOs;

    }
    
    @RequestMapping(value = "/1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
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
