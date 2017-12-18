package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Homepage controller
 *
 * @author Jan-Sebastian Fabik
 */

@Controller
@RequestMapping("/")
public class HomeController {

	private final static Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AlbumFacade albumFacade;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("browse_section", true);
	}

	/**
	 * Displays the homepage.
	 *
	 * @return JSP page name
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		log.debug("home()");
		model.addAttribute("albumsFromLastMonth", albumFacade.getAlbumsFromLastMonth());
		return "home/home";
	}
}
