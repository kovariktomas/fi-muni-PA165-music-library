package cz.fi.muni.pa165.musiclibrary.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/logout")
public class LogoutController extends BaseController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String logout(
		HttpServletRequest request,
		RedirectAttributes redirectAttributes,
		Locale locale,
		SessionStatus status
	) {
		status.setComplete();
		request.getSession().removeAttribute("authenticatedUser");

		String flashMessage = messageSource.getMessage("logout.succeeded", null, locale);
		redirectAttributes.addFlashAttribute("alert_success", flashMessage);
		return "redirect:/";
	}
}
