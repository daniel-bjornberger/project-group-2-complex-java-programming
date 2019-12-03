package se.iths.complexjavaproject.mudders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class ActionController {

    @GetMapping("/rest/{userName}/{characterName}")
    public String rest(@PathVariable("userName") String userName,
                       @PathVariable("characterName") String characterName) {

        return "rest";

    }

    // TODO: Vad behöver skickas med från klienten? userName och characterName?


    // travel, attack, flee osv.

}
