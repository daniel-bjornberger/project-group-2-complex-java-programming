package se.iths.complexjavaproject.mudders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlPageController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

}
