package se.iths.complexjavaproject.mudders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemAmountModel {

    private String characterName;
    private String itemName;
    private int amount;

}
