package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "tavern")
@NoArgsConstructor
@ToString
@Service
public class Tavern extends NonPlayerCharacter implements TavernInterface {

    private static final long serialVersionUID = 7074554391312286112L;


    @Override
    public PlayerCharacter restAtTavern(PlayerCharacter playerCharacter) {
        int price = 2;
        int heal = 3;
        if(playerCharacter.getCurrency() >= price) {
            System.out.println("Your money ----------- "+ playerCharacter.getCurrency());
            playerCharacter.setCurrency(playerCharacter.getCurrency() - price);
            System.out.println("--------------- You recover "+heal+" health ---------------");
            playerCharacter.setHealth(playerCharacter.getHealth() + heal);
            System.out.println("Your money after visit----------- "+playerCharacter.getCurrency());
            /*if(playerCharacter.getHealth() >= playerCharacter.getMaxHealth()){
                playerCharacter.setHealth(playerCharacter.getMaxHealth());
            }*/
            System.out.println("--------------- You now have "+playerCharacter.getHealth()+ " health ---------------");
            return playerCharacter;
        }
        else
            System.out.println("Not enough money");
        return playerCharacter;
    }
}
