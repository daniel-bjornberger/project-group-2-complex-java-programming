package se.iths.complexjavaproject.mudders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;

@Service
@Controller
@RequestMapping("/player")
public class PlayerCharacterController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private PlayerCharacterService playerCharacterService;
    /*private TravelService travelService;
    private TownService townService;
    private UserService userService;*/

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService/*, TravelService travelService, TownService townService, UserService userService*/) {
        this.playerCharacterService = playerCharacterService;
        /*this.travelService = travelService;
        this.townService = townService;
        this.userService = userService;*/
    }

    /**
     * Hard coded to find playerCharacter with long id 1, sends the name of the character to playercharacter.html
     //* @param model
     //* @return playercharacter.html
     */
    /*@RequestMapping(value="/playercharacter", method = RequestMethod.GET )
    public String read(Model model){
        try {
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                email = authentication.getName();
            }
            User user = userService.findUserByEmail(email);
            PlayerCharacterModel player = playerCharacterService.findByUserId(user.getId());
            model.addAttribute("player", player.getCharacterName());
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Only one playerCharacter allowed!");
            log.error("Possible authentication error!");
            return "error";
        }
        return "playercharacter";
    }*/

    /*@GetMapping("/playgame")
    public String playGame(Model model, @RequestParam String characterName ){
        try {
            PlayerCharacterModel player = playerCharacterService.findCharacterByName(characterName).toModel();
            model.addAttribute("player", player);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to send playerCharacter to play.html");
            return "error";
        }
        return "play";
    }*/


    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewPlayerCharacter (@RequestParam String characterName) {
        log.debug("Add new playerCharacter");
        try {
            String email = "";
            /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                email = authentication.getName();
                log.warn("Authenticating");
            }*/
            //playerCharacterService.createNewCharacter(characterName, email);
            //return "playercharacter";
            return ResponseEntity.ok().body(playerCharacterService.createNewCharacter(characterName, email));

        } catch (Exception e) {
            //e.printStackTrace();
            //log.error("Unable to add new playerCharacter");
            //return "error";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllPlayers() {
        try {
            return ResponseEntity.ok().body(playerCharacterService.findAll());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




    /**
     * Allows playerCharacter to travel around figthing or finding gold, unless they are dead.
     //* @param model
     //* @param characterName
     //* @return play.html, error.html, playercharacter.html
     */
    /*@GetMapping(path = "/travel")
    public String getTravelPlayerByName(Model model, @RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travel(characterName);
            if (playerCharacterModel.getHealth() == 0){
                removePlayer(playerCharacterModel.getCharacterName());
                return "playercharacter";
            }
            model.addAttribute("player", playerCharacterModel);
            return "play";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to use endpoint /travel");
            return "error";
        }
    }*/

    /**
     * Allows playerCharacter to travel to next town.
     //* @param model
     //* @param characterName
     //* @return play.html or error.html
     */
        /*@GetMapping(path = "/travel/next")
    public String travelToNextTown(Model model, @RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travelToNextTown(characterName);
            model.addAttribute("player", playerCharacterModel);
            return "play";
        } catch (BadDataException e) {
            e.printStackTrace();
            log.error("Unable to use endpoint travel/next");
            return "error";
        }
    }*/

    /*@GetMapping(path = "/travel/previous")
    public String travelToPreviousTown(Model model, @RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travelToPreviousTown(characterName);
            model.addAttribute("player", playerCharacterModel);
            return "play";
        } catch (BadDataException e) {
            e.printStackTrace();
            log.error("Unable to use endpoint travel/next");
            return "error";
        }
    }*/

    /**
     * Uses visitHealer() in townService to heal playerCharacter if conditions are met.
     //* @param model
     //* @param characterName
     //* @return play.html
     */
    /*@GetMapping(path = "/healer")
    public String goToHealer(Model model, @RequestParam String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = townService.visitHealer(characterName);
            model.addAttribute("player", playerCharacterModel);
            return "play";

        } catch (Exception e) {
            log.error("Unable to visit Healer");
            return "error";
        }
    }*/

    /**
     * Uses visitTavern() in townService to allow minor healing if conditions are met.
     //* @param model
     //* @param characterName
     //* @return play.html
     */
    /*@GetMapping(path = "/tavern")
    public String playerVisitTavern(Model model, @RequestParam String characterName){
        try{
            PlayerCharacterModel playerCharacterModel = townService.visitTavern(characterName);
            model.addAttribute("player", playerCharacterModel);
            return "play";
        } catch (Exception e) {
            log.error("Unable to visit Tavern");
            return "error";
        }
    }*/

    /**
     * Removes playerCharacter from database.
     //* @param characterName
     //* @return
     */
    /*@GetMapping(path = "/delete")
    public String removePlayer(@RequestParam String characterName) {
        try {
            PlayerCharacter character = playerCharacterService.findCharacterByName(characterName);
            playerCharacterService.deleteCharacter(character);
            return "playercharacter";
        } catch (Exception e) {
            return "error";
        }
    }*/
}
