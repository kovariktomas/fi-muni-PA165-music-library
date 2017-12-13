/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.musiclibrary.web.controllers;

import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;
import cz.fi.muni.pa165.musiclibrary.facade.ApplicationUserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class LoginController
{

    @Inject
    ApplicationUserFacade userFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:/";
        }

        model.addAttribute("userLogin", new ApplicationUserDTO());
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

        ApplicationUserDTO found = userFacade.findByEmail(form.getEmail());

        if (found == null ) // missing facade check
        {
            redirectAttributes.addFlashAttribute("alert_warning", "Login with username "+form.getEmail()+" has failed.");
            return "redirect:" + uriBuilder.path("/login").toUriString();
        }

        request.getSession().setAttribute("authenticatedUser", found);

        redirectAttributes.addFlashAttribute("alert_success", "Login was successful");
        return "redirect:" + uriBuilder.path("/").toUriString();
    }
}

