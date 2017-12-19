package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Controller for albums administration.
 *
 * @author Iva Liberova
 * @author Jan-Sebastian Fabik
 */
@Controller
@RequestMapping("/album")
public class AlbumController extends BaseController {

	private final static Logger log = LoggerFactory.getLogger(AlbumController.class);

	@Autowired
	private AlbumFacade albumFacade;

	@Autowired
	private SongFacade songFacade;

	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("albums_section", true);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		log.debug("list()");
		model.addAttribute("albums", albumFacade.findAll());
		return "album/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, Locale locale) {
		log.debug("detail({})", id);

		AlbumDTO album = albumFacade.findById(id);

		if (album == null) {
			String flashMessage = messageSource.getMessage("albums.detail.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/album/list";
		}

		model.addAttribute("album", album);
		model.addAttribute("songs", songFacade.findByAlbum(id));
		return "album/detail";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		log.debug("create()");
		model.addAttribute("albumCreate", new AlbumCreateFormData());
		return "album/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(
		@Valid @ModelAttribute("albumCreate") AlbumCreateFormData albumData,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes,
		UriComponentsBuilder uriBuilder,
		Locale locale
	) {
		log.debug("create({})", albumData);
		AlbumCreateDTO albumCreate = albumData.toAlbumCreateDTO();

		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", error);
			}
			for (FieldError error : bindingResult.getFieldErrors()) {
				model.addAttribute(error.getField() + "_error", true);
				log.trace("FieldError: {}", error);
			}
			return "album/create";
		}

		Long id = albumFacade.create(albumCreate);

		String flashMessage = messageSource.getMessage(
			"albums.create.saved",
			new Object[]{albumCreate.getTitle()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:" + uriBuilder.path("/album/detail/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(
		@PathVariable long id,
		Model model,
		RedirectAttributes redirectAttributes,
		Locale locale
	) {
		log.debug("edit({})", id);
		AlbumDTO album = albumFacade.findById(id);

		if (album == null) {
			String flashMessage = messageSource.getMessage("albums.edit.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/album/list";
		}

		model.addAttribute("album", album);
		return "album/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(
		@PathVariable long id,
		@Valid @ModelAttribute("album") AlbumEditFormData albumData,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes,
		UriComponentsBuilder uriBuilder,
		Locale locale
	) {
		log.debug("edit({}, {})", id, albumData);
		AlbumDTO album = albumFacade.findById(id);

		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", error);
			}
			for (FieldError error : bindingResult.getFieldErrors()) {
				model.addAttribute(error.getField() + "_error", true);
				log.trace("FieldError: {}", error);
			}
			return "album/edit";
		}

		albumData.updateAlbumDTO(album);
		albumFacade.update(album);

		String flashMessage = messageSource.getMessage(
			"albums.edit.saved",
			new Object[]{album.getTitle()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:" + uriBuilder.path("/album/detail/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(
		@PathVariable long id,
		RedirectAttributes redirectAttributes,
		Locale locale
	) {
		log.debug("delete({})", id);
		AlbumDTO album = albumFacade.findById(id);

		if (album == null) {
			String flashMessage = messageSource.getMessage("albums.delete.notFound", null, locale);
			redirectAttributes.addFlashAttribute("alert_danger", flashMessage);
			return "redirect:/album/list";
		}

		albumFacade.delete(id);

		String flashMessage = messageSource.getMessage(
			"albums.delete.deleted",
			new Object[]{album.getTitle()},
			locale
		);

		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:/album/list";
	}

	@InitBinder
	@SuppressWarnings("unused")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
			new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true, 10));

		if (binder.getTarget() instanceof AlbumCreateFormData) {
			binder.addValidators(new AlbumCreateFormDataValidator());
		}

		if (binder.getTarget() instanceof AlbumEditFormData) {
			binder.addValidators(new AlbumEditFormDataValidator());
		}
	}
}
