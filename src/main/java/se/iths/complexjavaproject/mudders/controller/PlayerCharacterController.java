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
import org.springframework.web.servlet.ModelAndView;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.service.IPCServiceStub;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TownService;
import se.iths.complexjavaproject.mudders.service.TravelService;

import java.util.List;

@Service
@Controller("/player")
public class PlayerCharacterController {

    private PlayerCharacterService playerCharacterService;
    private TravelService travelService;
    private TownService townService;

    @Autowired
    private IPCServiceStub ipcServiceStub;

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService, TravelService travelService, TownService townService) {
        this.playerCharacterService = playerCharacterService;
        this.travelService = travelService;
        this.townService = townService;
    }

    @RequestMapping(value="/playercharacter", method = RequestMethod.GET )
    public String read(Model model){
        //List<PlayerCharacterModel> playerCharacterModel = playerCharacterService.findAll();

        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();
        playerCharacterModel.setCharacterName("TestSettingName");


        model.addAttribute("player", playerCharacterModel.getCharacterName());
        return "playercharacter";
    }

   /*@RequestMapping(value="/add")
    public String savecharacter(PlayerCharacterModel playerCharacterModel){
        playerCharacterModel.setCharacterName("Test");

        return "playercharacter";

    }*/

    @RequestMapping(value = "/playercharacter", method = RequestMethod.POST)
    public String addNewPlayerCharacter (@RequestParam String characterName) {
        try {
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                email = authentication.getName();
            }
            PlayerCharacterModel characterModel = playerCharacterService.createNewCharacter(characterName, email);
            return "playercharacter";
        } catch (Exception e) {
            return "playercharacter";
        }
    }


   /* @GetMapping(path = "/all")
    public ResponseEntity getAllPlayers() {
        try {
            return ResponseEntity.ok().body(playerCharacterService.findAll());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

   /* @PostMapping(path = "/add")
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
    }*/

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
