package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.GenreCreateDTOValidator;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.context.MessageSource;

/**
 * SpringMVC Controller for search in genresm, songs, albums, musicians.
 *
 * @author Tomas Kovarik
 */

@Controller
@RequestMapping("/search")
public class SearchController {

	final static Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private GenreFacade genreFacade;
	
	@Autowired
	private AlbumFacade albumFacade;
	
	@Autowired
	private MusicianFacade musicianFacade;
	
	@Autowired
	private SongFacade songFacade;
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * Shows a search result.
	 *
	 * @param pSearchTerm
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value="/result", method = RequestMethod.GET)
	public String Search(@RequestParam(value = "searchTerm", defaultValue = "", required = false) String pSearchTerm, Model model, Locale loc) {
		model.addAttribute("genres", genreFacade.findByName(pSearchTerm));
		model.addAttribute("musicians", musicianFacade.findByName(pSearchTerm));
		model.addAttribute("songs", songFacade.findByTitle(pSearchTerm));
		model.addAttribute("albums", albumFacade.findByTitle(pSearchTerm));
		model.addAttribute("searchTerm", pSearchTerm);
		return "/search/result";
	}
	
	 /**
     * Shows a album image.
     *
     * @param id       product id
	 * @param request
     * @param response HTTP response
     * @throws IOException
     */
    @RequestMapping("/albumImage/{id}")
    public void albumImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AlbumDTO albumDTO = albumFacade.findById(id);
        byte[] image = albumDTO.getAlbumArt();
        if(image==null) {
            response.sendRedirect(request.getContextPath()+"/no-image.png");
        } else {
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }
}