package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TownService;
import se.iths.complexjavaproject.mudders.service.TravelService;

import java.util.List;

@Service
@Controller
@RequestMapping("/player")
public class PlayerCharacterController {

    private PlayerCharacterService playerCharacterService;
    private TravelService travelService;
    private TownService townService;

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService, TravelService travelService, TownService townService) {
        this.playerCharacterService = playerCharacterService;
        this.travelService = travelService;
        this.townService = townService;
    }

    /**
     * Hard coded to find playerCharacter with long id 1, sends the name of the character to playercharacter.html
     * @param model
     * @return playercharacter.html
     */
    @RequestMapping(value="/playercharacter", method = RequestMethod.GET )
    public String read(Model model){
        PlayerCharacterModel player = playerCharacterService.findById(1);
        model.addAttribute("player", player.getCharacterName());

        return "playercharacter";
    }

    @RequestMapping(value="/savecharacter", method = RequestMethod.GET )
    public String saveCharacter(PlayerCharacterModel playerCharacterModel){
        playerCharacterModel.setLevel(10);

        return "playercharacter";
    }

    @GetMapping("/addplayer")
    //RequestMapping annotation over a method - tie out to a browser/postman
    //value - relative path (endpoint) from a domain name.
    //method - HTTP method
    public String addPlayer(Model model, @RequestParam(value="characterName", required=false, defaultValue="0.0")String characterName){
        PlayerCharacterModel playerCharacterModel = playerCharacterService.findById(1);
        playerCharacterModel.setCharacterName(characterName);

        model.addAttribute("player", playerCharacterModel);
        return "start";
    }


    /*@RequestMapping(value="/playercharacter", method=RequestMethod.GET)
    public String playGame(){
        //send playerCharacter into game
        //enter new page - play.html
        return "play";
    }*/


    @GetMapping(path = "/all")
    public ResponseEntity getAllPlayers() {
        try {
            return ResponseEntity.ok().body(playerCharacterService.findAll());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity addNewPlayerCharacter (@RequestParam String characterName) {
        try {
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                email = authentication.getName();
            }
            PlayerCharacterModel characterModel = playerCharacterService.createNewCharacter(characterName, email);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping(path = "/travel")
    public ResponseEntity getTravelPlayerByName(@RequestBody String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travel(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/travel/next")
    public ResponseEntity travelToNextTown(@RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travelToNextTown(characterName);
            return  ResponseEntity.status(HttpStatus.OK).body(playerCharacterModel);
        } catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/travel/previous")
    public ResponseEntity travelToPreviousTown(@RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travelToPreviousTown(characterName);
            return ResponseEntity.status(HttpStatus.OK).body(playerCharacterModel);
        } catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/healer")
    public ResponseEntity goToHealer(@RequestBody String characterName){
        try{
            PlayerCharacterModel playerCharacterModel = townService.visitHealer(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (BadDataException e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/tavern")
    public ResponseEntity playerVisitTavern(@RequestBody String characterName){
        try{
            PlayerCharacterModel playerCharacterModel = townService.visitTavern(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity removePlayer(@RequestParam String characterName) {
        try {
            PlayerCharacter character = playerCharacterService.findCharacterByName(characterName);
            playerCharacterService.deleteCharacter(character);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
