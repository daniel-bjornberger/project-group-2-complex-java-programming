package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "shop")
@NoArgsConstructor
@ToString
public class Shop extends NonPlayerCharacter  {

    private static final long serialVersionUID = 4552624858238223436L;

    public void shopIntro(){
        System.out.println("Welcome! How can I help you?");
        System.out.println("I would like to buy some potions.");
        System.out.println("Ah ofcourse! We are the finest purveyors of potions this side of Langaria.");
    }

    public PlayerCharacter shopping(PlayerCharacter playerCharacter){
         int price = 5;

         if(playerCharacter.getCurrency() >= price){
             System.out.println("The price of a potion bottle is 5 coins.");
             System.out.println("Yes it is a steep price but demand is always high.");
             System.out.println("I have enough"+ playerCharacter.getCurrency());
             playerCharacter.setCurrency(playerCharacter.getCurrency() - price);
             //Add potion to inventory

             System.out.println("Inventory now contains " + "playerCharacter.inventory.potions.amount" + " potions");
             return playerCharacter;
         }
         else{
             System.out.println("I can't afford that right now.");
             System.out.println("Oh well, come again when you can.");
             return playerCharacter;
         }

    }
}
