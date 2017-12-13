package cz.fi.muni.pa165.musiclibrary.web.controller;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.GenreCreateDTOValidator;
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

/**
 * SpringMVC Controller for administering genres.
 *
 * @author Tomas Kovarik
 */

@Controller
@RequestMapping("/genre")
public class GenreController {

	final static Logger log = LoggerFactory.getLogger(GenreController.class);

	@Autowired
	private GenreFacade genreFacade;

	/**
	 * Shows a list of genres with the ability to add, delete or edit.
	 *
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("genres", genreFacade.findAll());
		return "genre/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
		GenreDTO genre = genreFacade.findById(id);
		genreFacade.delete(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success", "Žánr \"" + genre.getName() + "\" byl smazán.");
		return "redirect:" + uriBuilder.path("/genre/list").toUriString();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		log.debug("view({})", id);
		model.addAttribute("genre", genreFacade.findById(id));
		return "genre/view";
	}

	/**
	 * Prepares an empty form.
	 *
	 * @param model data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newGenre(Model model) {
		log.debug("new()");
		model.addAttribute("genreCreate", new GenreCreateDTO());
		return "genre/new";
	}

	/**
	 * Spring Validator added to JSR-303 Validator for this @Controller only.
	 * It is useful  for custom validations that are not defined on the form bean by annotations.
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof GenreCreateDTO) {
			binder.addValidators(new GenreCreateDTOValidator());
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("genreCreate") GenreCreateDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("create(genreCreate={})", formBean);
		//in case of validation error forward back to the the form
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "genre/new";
		}
		//create product
		Long id = genreFacade.create(formBean);
		//report success
		redirectAttributes.addFlashAttribute("alert_success", "Žánr " + id + " byl vytvořen");
		return "redirect:" + uriBuilder.path("/genre/view/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String showUpdateGenreForm(@PathVariable long id, Model model) {
		log.debug("update()");
		GenreDTO genre = genreFacade.findById(id);
		model.addAttribute("genre", genre);
		model.addAttribute("genreUpdate", genre);
		return "genre/update";
	}

	@RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("genreUpdate") GenreDTO formBean, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("update(genreUpdate={})", formBean);
		//in case of validation error forward back to the the form
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "genre/update";
		}
		//update genre
		genreFacade.update(formBean);
		Long id = formBean.getId();
		//report success
		redirectAttributes.addFlashAttribute("alert_success", "Žánr " + id + " byl upraven");
		return "redirect:" + uriBuilder.path("/genre/view/{id}").buildAndExpand(id).encode().toUriString();
	}
}
