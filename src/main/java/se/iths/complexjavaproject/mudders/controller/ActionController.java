package se.iths.complexjavaproject.mudders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class ActionController {

    @GetMapping("/rest/{characterName}")
    public String rest(@PathVariable("characterName") String characterName) {

        return "rest";

    }


    @GetMapping("/attack/{characterName}")
    public String attack(@PathVariable("characterName") String characterName) {

        return "attack";

    }


    @GetMapping("/travel/{characterName}")
    public String travel(@PathVariable("characterName") String characterName) {

        return "travel";

    }


    @GetMapping("/flee/{characterName}")
    public String flee(@PathVariable("characterName") String characterName) {

        return "flee";

    }




    // travel, attack, flee osv.

}
