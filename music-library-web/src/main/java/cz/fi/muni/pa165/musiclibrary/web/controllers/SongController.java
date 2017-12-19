package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.*;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.SongCreateDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author David
 */

@Controller
@RequestMapping("/song")
public class SongController extends BaseController {

	@Autowired
	private SongFacade songFacade;

	@Autowired
	private GenreFacade genreFacade;

	@Autowired
	private AlbumFacade albumFacade;

	@Autowired
	private MusicianFacade musicianFacade;

	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("songs_section", true);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("songs", songFacade.findAll());
		return "song/list";
	}

	/**
	 * Prepares an empty form.
	 *
	 * @param model data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newSong(Model model) {
		model.addAttribute("songCreate", new SongCreateDTO());
		model.addAttribute("genres", genreFacade.findAll());
		model.addAttribute("albums", albumFacade.findAll());
		model.addAttribute("musicians", musicianFacade.findAll());
		return "song/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(
		@Valid @ModelAttribute("songCreate") SongCreateDTO formBean,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes,
		UriComponentsBuilder uriBuilder,
		Locale locale
	) {
		model.addAttribute("genres", genreFacade.findAll());
		model.addAttribute("albums", albumFacade.findAll());
		model.addAttribute("musicians", musicianFacade.findAll());

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach((fe) -> {
				model.addAttribute(fe.getField() + "_error", true);
			});
			return "song/create";
		}

		Long id = songFacade.create(formBean);
		String flashMessage = messageSource.getMessage("songs.create.saved", new Object[]{id}, locale);
		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:" + uriBuilder.path("/song/list").toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(
		@PathVariable long id,
		RedirectAttributes redirectAttributes,
		Locale locale
	) {
		SongDTO song = songFacade.findById(id);
		songFacade.delete(id);
		String flashMessage = messageSource.getMessage("songs.delete.deleted", new Object[]{song.getTitle()}, locale);
		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:/song/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("song", songFacade.findById(id));
		return "song/detail";
	}

	/**
	 * Spring Validator added to JSR-303 Validator for this @Controller only.
	 * It is useful  for custom validations that are not defined on the form bean by annotations.
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof SongCreateDTO) {
			binder.addValidators(new SongCreateDTOValidator());
		}
	}

}
