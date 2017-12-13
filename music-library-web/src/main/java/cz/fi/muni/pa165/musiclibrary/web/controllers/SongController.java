package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.web.forms.SongCreateDTOValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author David
 */

@Controller
@RequestMapping("/song")
public class SongController {
    
    @Autowired
    private SongFacade songFacade;
    
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
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSong(Model model) {
        model.addAttribute("songCreate", new SongCreateDTO());
        //model.addAttribute("genres", genreFacade.findAll());
        //model.addAttribute("albums", albumFacade.findAll());
        //model.addAttribute("musicians", musicianFacade.findAll());
        return "song/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("songCreate") SongCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", true);
            });
            return "song/new";
        }
        
        Long id = songFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Song " + id + " was created");
        return "redirect:" + uriBuilder.path("/song/list").toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        SongDTO song = songFacade.findById(id);
        songFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Song " + song.getTitle() + " was created");
        return "redirect:" + uriBuilder.path("/song/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("song", songFacade.findById(id));
        return "song/view";
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
