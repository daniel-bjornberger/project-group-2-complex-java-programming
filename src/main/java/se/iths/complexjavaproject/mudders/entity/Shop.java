package se.iths.complexjavaproject.mudders.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
        System.out.println("Ah of course! We are the finest purveyors of potions this side of Langaria.");
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



// ********* Förslag på ändringar att införa i denna klass, ifall arbetet hade fortsatt /Daniel


/*
package se.iths.complexjavaproject.mudders.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.ItemNotFoundException;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;
import se.iths.complexjavaproject.mudders.repository.ItemRepository;
import se.iths.complexjavaproject.mudders.service.ItemService;

import javax.persistence.Table;
import java.util.Optional;

@Getter
@Setter
@Table(name = "shop")
@NoArgsConstructor
@ToString
public class Shop extends NonPlayerCharacter  {

    private static final long serialVersionUID = 4552624858238223436L;

    private ItemRepository itemRepository;

    private ItemService itemService;

    // '@Autowired' ska inte finnas i en entity-klass. Metoden 'shopping' nedan borde istället
    // ligga i klassen 'TownService'.

    @Autowired
    public Shop(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService    = itemService;
    }


    public void shopIntro(){
        System.out.println("Welcome! How can I help you?");
        System.out.println("I would like to buy some potions.");
        System.out.println("Ah of course! We are the finest purveyors of potions this side of Langaria.");
    }

    public PlayerCharacter shopping(PlayerCharacter playerCharacter, String itemName, int amount){
        //int price = 5;

        Optional<Item> optionalItem = itemRepository.findByName(itemName);

        if (optionalItem.isEmpty()) {
            System.out.println("Sorry, we don't have any " + itemName + "s.");
            return playerCharacter;
        }

        Item item = optionalItem.get();

        if(playerCharacter.getCurrency() >= amount * item.getPrice()){

            //System.out.println("The price of a potion bottle is 5 coins.");
            System.out.println("The price for " + amount + " " + itemName + "(s) is " + amount * item.getPrice() + ".");
            System.out.println("Yes it is a steep price but demand is always high.");
            //System.out.println("I have enough"+ playerCharacter.getCurrency());
            playerCharacter.setCurrency(playerCharacter.getCurrency() - amount * item.getPrice());
            //Add potion to inventory

            try {
                itemService.addItemToPlayerCharacter(new ItemAmountModel(playerCharacter.getCharacterName(),
                        itemName, amount));
            } catch (BadDataException | ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }

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
*/

