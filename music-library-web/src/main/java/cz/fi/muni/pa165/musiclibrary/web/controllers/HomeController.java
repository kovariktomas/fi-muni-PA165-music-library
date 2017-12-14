package cz.fi.muni.pa165.musiclibrary.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Homepage controller
 *
 * @author Jan-Sebastian Fabik
 */

@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Displays the homepage.
     *
     * @return JSP page name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home/home";
    }
}
