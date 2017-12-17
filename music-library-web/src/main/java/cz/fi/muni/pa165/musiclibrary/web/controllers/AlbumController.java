package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.AlbumCreateDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.MessageSource;
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
 * SpringMVC Controller for administering albums.
 *
 * @author Iva Liberova
 */

@Controller
@RequestMapping("/album")
public class AlbumController {

    final static Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumFacade albumFacade;

    @Autowired
    private MessageSource messageSource;

    /**
     * Shows a list of albums with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("albums", albumFacade.findAll());
        return "album/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, Locale loc) {
        AlbumDTO album = albumFacade.findById(id);
        albumFacade.delete(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", String.format(messageSource.getMessage("albumMessage.successDelete", null, loc), album.getTitle()));
        return "redirect:" + uriBuilder.path("/album/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("album", albumFacade.findById(id));
        return "album/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAlbum(Model model) {
        log.debug("new()");
        model.addAttribute("albumCreate", new AlbumCreateDTO());
        return "album/new";
    }

    /**
     * Spring Validator added to JSR-303 Validator for this @Controller only.
     * It is useful  for custom validations that are not defined on the form bean by annotations.
     * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AlbumCreateDTO) {
            binder.addValidators(new AlbumCreateDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("albumCreate") AlbumCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale loc) {
        log.debug("create(albumCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "album/new";
        }
        Long id = albumFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_success", String.format(messageSource.getMessage("albumMessage.successAdd", null, loc), id));
        return "redirect:" + uriBuilder.path("/album/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showUpdateAlbumForm(@PathVariable long id, Model model) {
        log.debug("update()");
        AlbumDTO album = albumFacade.findById(id);
        model.addAttribute("album", album);
        model.addAttribute("albumUpdate", album);
        return "album/update";
    }

    @RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("albumUpdate") AlbumDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale loc) {
        log.debug("update(albumUpdate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "album/update";
        }
        //update album
        albumFacade.update(formBean);
        Long id = formBean.getId();
        //report success
        redirectAttributes.addFlashAttribute("alert_success", String.format(messageSource.getMessage("albumMessage.successEdit", null, loc), id));
        return "redirect:" + uriBuilder.path("/album/view/{id}").buildAndExpand(id).encode().toUriString();
    }
}
