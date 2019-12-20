package se.iths.complexjavaproject.mudders.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TownService;

import java.sql.SQLOutput;

@Service
public class Healer extends NonPlayerCharacter implements NpcHealer {

    private static final long serialVersionUID = 8106546763483546385L;

    @Override
    public PlayerCharacter doctor(PlayerCharacter player) {

        int price = 15;

        if(player.getCurrency() < price){
            System.out.println("Sorry, you have insufficient funds!");
            //townService.greeterMessage();
        }
        else if(player.getCurrency() > price){
            System.out.println("Oh no! You're bleeding! Medic!! Man Down!");
            player.setCurrency(player.getCurrency() - price);
            player.setHealth(player.getMaxHealth());
            System.out.println("One minor All Cure potion and voila! You're ready to face the world again.");
        }

        return player;
    }
}
