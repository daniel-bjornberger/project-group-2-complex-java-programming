package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.entity.Town;

import java.util.ArrayList;

@Getter
@Setter
public class PlayerCharacterModel {

    private String characterName;
    private int experience;
    private int health;
    private int level;
    private Town currentTown;
    private int damage;
    private int currency;
    private ArrayList<ItemAmountModel> itemAmountModels = new ArrayList<>();

}
