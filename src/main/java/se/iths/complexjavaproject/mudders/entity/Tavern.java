package se.iths.complexjavaproject.mudders.entity;

public class Tavern extends NonPlayerCharacter implements TavernInterface {

    private static final long serialVersionUID = 7074554391312286112L;


    @Override
    public PlayerCharacter restAtTavern(PlayerCharacter playerCharacter) {
        int price = 2;
        int heal = 3;
        if(playerCharacter.getCurrency() >= price){
            System.out.println("--------------- You recover "+heal+" health ---------------");
            System.out.println("--------------- You now have "+playerCharacter.getHealth()+ "health ---------------");
            playerCharacter.setHealth(playerCharacter.getHealth() + heal);
            return playerCharacter;
        }
        else
            System.out.println("Not enough money");
        return playerCharacter;
    }
}
