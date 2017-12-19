package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.MusicianCreateDTOValidator;
import cz.fi.muni.pa165.musiclibrary.web.forms.MusicianDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import java.util.Locale;

/**
 * Controller for musicians administration.
 *
 * @author Jan-Sebastian Fabik
 */
@Controller
@RequestMapping("/musician")
public class MusicianController extends BaseController {

	private final static Logger log = LoggerFactory.getLogger(MusicianController.class);

	@Autowired
	private MusicianFacade musicianFacade;

	@Autowired
	private AlbumFacade albumFacade;

	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("musicians_section", true);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		log.debug("list()");
		model.addAttribute("musicians", musicianFacade.findAll());
		return "musician/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, Locale locale) {
		log.debug("detail({})", id);

		MusicianDTO musician = musicianFacade.findById(id);

		if (musician == null) {
			String flashMessage = messageSource.getMessage("musicians.detail.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/musician/list";
		}

		model.addAttribute("musician", musician);
		model.addAttribute("albums", albumFacade.findByMusician(id));
		return "musician/detail";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		log.debug("create()");
		model.addAttribute("musicianCreate", new MusicianCreateDTO());
		return "musician/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(
		@Valid @ModelAttribute("musicianCreate") MusicianCreateDTO musicianCreate,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes,
		UriComponentsBuilder uriBuilder,
		Locale locale
	) {
		log.debug("create({})", musicianCreate);

		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", error);
			}
			for (FieldError error : bindingResult.getFieldErrors()) {
				model.addAttribute(error.getField() + "_error", true);
				log.trace("FieldError: {}", error);
			}
			return "musician/create";
		}

		Long id = musicianFacade.create(musicianCreate);

		String flashMessage = messageSource.getMessage(
			"musicians.create.saved",
			new Object[]{musicianCreate.getName()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:" + uriBuilder.path("/musician/detail/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, Locale locale) {
		log.debug("edit({})", id);
		MusicianDTO musician = musicianFacade.findById(id);

		if (musician == null) {
			String flashMessage = messageSource.getMessage("musicians.edit.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/musician/list";
		}

		model.addAttribute("musician", musician);
		return "musician/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(
		@PathVariable long id,
		@Valid @ModelAttribute("musician") MusicianDTO musician,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes,
		UriComponentsBuilder uriBuilder,
		Locale locale
	) {
		log.debug("edit({})", musician);

		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", error);
			}
			for (FieldError error : bindingResult.getFieldErrors()) {
				model.addAttribute(error.getField() + "_error", true);
				log.trace("FieldError: {}", error);
			}
			return "musician/edit";
		}

		musician.setId(id);
		musicianFacade.update(musician);

		String flashMessage = messageSource.getMessage(
			"musicians.edit.saved",
			new Object[]{musician.getName()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:" + uriBuilder.path("/musician/detail/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(
		@PathVariable long id,
		RedirectAttributes redirectAttributes,
		Locale locale
	) {
		log.debug("delete({})", id);
		MusicianDTO musician = musicianFacade.findById(id);

		if (musician == null) {
			String flashMessage = messageSource.getMessage("musicians.delete.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/musician/list";
		}
		
		if (albumFacade.findByMusician(musician.getId()).isEmpty() == false) {
 			String flashMessage = messageSource.getMessage("musicians.delete.couldnotbydeleted", null, locale);
 			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
 			return "redirect:/musician/list";
 		}

		musicianFacade.delete(id);

		String flashMessage = messageSource.getMessage(
			"musicians.delete.deleted",
			new Object[]{musician.getName()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:/musician/list";
	}

	@InitBinder
	@SuppressWarnings("unused")
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof MusicianCreateDTO) {
			binder.addValidators(new MusicianCreateDTOValidator());
		}

		if (binder.getTarget() instanceof MusicianDTO) {
			binder.addValidators(new MusicianDTOValidator());
		}
	}
}
