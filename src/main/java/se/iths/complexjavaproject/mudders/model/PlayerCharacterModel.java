package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCharacterModel {

    private String characterName;
    private int experience;
    private int health;
    private int level;
    private String currentTown;
    private int damage;
    private int currency;


}
