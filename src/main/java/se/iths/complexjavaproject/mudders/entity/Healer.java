package se.iths.complexjavaproject.mudders.entity;

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
            player.setCurrency(player.getCurrency() - price);
            player.setHealth(player.getMaxHealth());
            System.out.println("One minor All Cure potion and voila! You're ready to face the world again.");
        }

        return player;
    }
}
