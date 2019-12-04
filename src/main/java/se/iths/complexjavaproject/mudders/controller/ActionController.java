package se.iths.complexjavaproject.mudders.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.complexjavaproject.mudders.service.CombatService;

@RestController
@RequestMapping("/action")
public class ActionController {

    private CombatService combatService;

    @GetMapping(value = "/rest/{characterName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse rest(@PathVariable("characterName") String characterName) {

        return new ActionControllerResponse("rest, " + characterName, true, true, false, true);

    }


    @GetMapping(value = "/attack/{characterName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse attack(@PathVariable("characterName") String characterName) {

        return new ActionControllerResponse("attack, " + characterName, false, true, false, true);

    }


    @GetMapping(value = "/travel/{characterName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse travel(@PathVariable("characterName") String characterName) {

        return new ActionControllerResponse("travel, " + characterName, true, false, false, false);

    }


    @GetMapping(value = "/flee/{characterName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse flee(@PathVariable("characterName") String characterName) {

        return new ActionControllerResponse("flee, " + characterName, true, false, false, false);

    }




    // travel, attack, flee osv.

}
