package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;
import cz.fi.muni.pa165.musiclibrary.facade.ApplicationUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ApplicationUserFacade applicationUserFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("authenticatedUser") != null) {
			return "redirect:/";
		}

		model.addAttribute("userLogin", new ApplicationUserDTO());
		return "/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postLogin(@Valid @ModelAttribute("userLogin") ApplicationUserDTO form,
							BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
							UriComponentsBuilder uriBuilder, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				model.addAttribute(error.getField() + "_error", true);
			}

			model.addAttribute("userLogin", new ApplicationUserDTO());
			return "/login";
		}
		ApplicationUserDTO found;
		try {
			found = applicationUserFacade.findByEmail(form.getEmail());
		} catch (EmptyResultDataAccessException ex) {
			redirectAttributes.addFlashAttribute("alert_warning", "Login with email " + form.getEmail() + " has failed.");
			return "redirect:" + uriBuilder.path("/login").toUriString();
		}

		if (found == null || !applicationUserFacade.verifyPassword(found.getId(), form.getPassHash())) {
			redirectAttributes.addFlashAttribute("alert_warning", "Login with email " + form.getEmail() + " has failed.");
			return "redirect:" + uriBuilder.path("/login").toUriString();
		}

		request.getSession().setAttribute("authenticatedUser", found);

		redirectAttributes.addFlashAttribute("alert_success", "Login was successful");
		return "redirect:" + uriBuilder.path("/").toUriString();
	}
}

