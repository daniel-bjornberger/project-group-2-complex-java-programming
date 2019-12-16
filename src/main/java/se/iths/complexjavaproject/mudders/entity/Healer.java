package se.iths.complexjavaproject.mudders.entity;

import org.springframework.beans.factory.annotation.Autowired;
import se.iths.complexjavaproject.mudders.service.TownService;

public class Healer extends NonPlayerCharacter implements INpcVariety{


    private static final long serialVersionUID = 8106546763483546385L;

    @Autowired
    TownService townService;

    @Override
    public PlayerCharacter doctor(PlayerCharacter player) {

        int price = 15;

        if(player.getCurrency() < price){
            System.out.println("Sorry, you have insufficient funds!");
            townService.greeterMessage();
        }
        else if(player.getCurrency() > price){
            player.setCurrency(player.getCurrency() - price);
            player.setHealth(player.getMaxHealth());
            //future use a variable called max health to heal when health has increased.
            System.out.println("One minor All Cure potion and voila! You're ready to face the world again.");
        }

        return player;
    }
}
