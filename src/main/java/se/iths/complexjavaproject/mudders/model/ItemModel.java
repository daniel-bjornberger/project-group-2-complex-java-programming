package se.iths.complexjavaproject.mudders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.iths.complexjavaproject.mudders.entity.Item;
import se.iths.complexjavaproject.mudders.exception.BadDataException;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {

    private String name;
    private int value;
    private int damage;
    private int healthRecovery;
    private int maxAmount;
    //private ArrayList<ItemAmountModel> itemAmountModels = new ArrayList<>();

    public Item convertToEntity() throws BadDataException {

        if (this.getName() == null || this.getName().isBlank()) {
            throw new BadDataException("The name of the item is missing.");
        }

        if (this.getValue() < 0) {
            throw new BadDataException("The value can not be less than zero.");
        }

        if (this.getDamage() < 0) {
            throw new BadDataException("The damage can not be less than zero.");
        }

        if (this.getHealthRecovery() < 0) {
            throw new BadDataException("The health recovery can not be less than zero.");
        }

        if (this.getMaxAmount() < 1) {
            throw new BadDataException("The maximum amount can not be less than one.");
        }

        // TODO: Max-värden på damage, healthRecovery och maxAmount?

        return new Item(this.getName(), this.getValue(),
                this.getDamage(), this.getHealthRecovery(), this.getMaxAmount());

    }

}
