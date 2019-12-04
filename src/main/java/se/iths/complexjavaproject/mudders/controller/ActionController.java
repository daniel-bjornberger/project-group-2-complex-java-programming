package se.iths.complexjavaproject.mudders.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.service.CombatService;

@RestController
@RequestMapping("/action")
public class ActionController {

    private CombatService combatService;

    @PostMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse rest(@RequestBody PlayerCharacterModel playerCharacterModel) {

        return new ActionControllerResponse("rest, " + playerCharacterModel.getCharacterName(),
                true, true, false, true);

    }


    @PostMapping(value = "/travel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse travel(@RequestBody PlayerCharacterModel playerCharacterModel) {

        return new ActionControllerResponse("travel, " + playerCharacterModel.getCharacterName(),
                true, false, false, false);

    }


    @PostMapping(value = "/attack", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse attack(@RequestBody PlayerCharacterModel playerCharacterModel) {

        return new ActionControllerResponse("attack, " + playerCharacterModel.getCharacterName(),
                false, true, false, true);

    }


    @PostMapping(value = "/flee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionControllerResponse flee(@RequestBody PlayerCharacterModel playerCharacterModel) {

        return new ActionControllerResponse("flee, " + playerCharacterModel.getCharacterName(),
                true, false, false, false);

    }




    // travel, attack, flee osv.

}
