package bg.softuni.cookiesDemo.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
    // http://localhost:8080/cookies
    private static final String LANG_COOKIE_NAME = "lang";

    @GetMapping("/cookies")
    public String cookies(@CookieValue(name = "lang", defaultValue = "en") String lang, Model model) {
        model.addAttribute(LANG_COOKIE_NAME, lang);
        return "cookies";
    }

    @PostMapping("/cookies")
    public String submitCookies(@RequestParam(name = "lang") String languageFromHtmlForm, HttpServletResponse response) {
        Cookie langCookie = new Cookie(LANG_COOKIE_NAME, languageFromHtmlForm);
        response.addCookie(langCookie);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Preferred user language is: " + languageFromHtmlForm);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return "redirect:/cookies";
    }
}
