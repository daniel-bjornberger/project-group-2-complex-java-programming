package se.iths.complexjavaproject.mudders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlPageController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

}
