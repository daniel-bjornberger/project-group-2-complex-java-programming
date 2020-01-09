package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.*;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.NonPlayerCharacterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.NonPlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.repository.TownRepository;

import java.util.Optional;

@Service
public class TownService {

    private final TownRepository townRepository;
    private final Tavern tavern;
    private final Shop shop;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final NonPlayerCharacterRepository nonPlayerCharacterRepository;

    @Autowired
    public TownService(TownRepository townRepository, Tavern tavern, Shop shop, PlayerCharacterRepository playerCharacterRepository, NonPlayerCharacterRepository nonPlayerCharacterRepository) {
        this.townRepository = townRepository;
        this.tavern = tavern;
        this.shop = shop;
        this.playerCharacterRepository = playerCharacterRepository;
        this.nonPlayerCharacterRepository = nonPlayerCharacterRepository;
    }

    private Town findTown(String townName){
        return townRepository.findTownByTownName(townName);
    }

    Town findById(long id) throws BadDataException {
        Optional<Town> optionalTown = townRepository.findById(id);
        if (optionalTown.isPresent()) {
            return optionalTown.get();
        }
        else {
            throw new BadDataException("Could not find town with id: " + id);
        }
    }

    public long numberOfTowns() {
        return townRepository.count();
    }

    void saveTown(Town town) {
        townRepository.save(town);
    }

    private NonPlayerCharacterModel findNpcByName(String name) {
        return nonPlayerCharacterRepository.findByName(name).toModel();
    }

    public PlayerCharacterModel visitHealer(String characterName) throws BadDataException {
        Healer healer = new Healer();
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(characterName).getCharacterName());
        healer.doctor(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

    public String getTownGreeter(String townName, String npcName){

        /*findTown(townName).getNpcs();
        findNpcByName(npcName);*/ //get string message

        //NPC - Welcome to Town! What do you want do now?

        return null;
    }

    // TODO: Borde skapas i startup och användas genom town.getNpc -> innKeeper -> rest. Måste även först kolla om det finns en tavern i staden.
    public PlayerCharacterModel visitTavern(String requestBody) throws BadDataException{
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        tavern.restAtTavern(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }

    public PlayerCharacterModel visitShop(String requestBody) throws BadDataException{
        PlayerCharacter playerCharacter = playerCharacterRepository.findByCharacterName(PlayerCharacterService.convertToEntity(requestBody).getCharacterName());
        shop.shopping(playerCharacter);
        playerCharacterRepository.save(playerCharacter);
        return playerCharacter.toModel();
    }





    //TODO: create function to allow for NPC to greet player
    //TODO: create function to allow Player to choose options to do in town.
    public void greeterMessage(){
        String welcome = "Welcome weary traveller to our magnificent city!";
        String options = "What would you like to do next?"
                +"\nInput the corresponding digit to make your choice:"
                +"\nVisit the Pub 5 copper: 1"
                +"\nVisit the blacksmith: 2"
                +"\nTreasure Hunt: 3"
                +"\nVisit the doctor and recover your health, 15 copper: 4"
                +"\nTravel to the next town: 5";

        //sout("You visit the pub and got drunk")

        int option = 0;

        System.out.println(welcome);
        System.out.println(options);

        // wait for input

        switch(option){
            case 1:
                System.out.println("Let me show you to the Pub");
                //get pub npc
                break;
            case 2:
                System.out.println("Let me show you the way!");
                //get blacksmith npc
                break;
            case 3:
                System.out.println("Cool! Have fun.");
                //potOfGold()??
                break;
            case 4:
                System.out.println("Oh no you're bleeding!! Medic! Man Down!");
                //get player send to doctor method.
                //doctor();
                break;
            case 5:
                System.out.println("Oh ok, Goodbye then and safe travels");
                //get player send to travel method.
                //world.travel();
                break;
        }

    }

    /*
     * Blacksmith - Welcome traveller! How can I help you?
     * Options: fix broken weapon, upgrade weapon.
     * Smithy
     *
     * */
}
