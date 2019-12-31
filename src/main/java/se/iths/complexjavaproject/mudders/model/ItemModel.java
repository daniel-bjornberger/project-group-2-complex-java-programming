package se.iths.complexjavaproject.mudders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.iths.complexjavaproject.mudders.entity.Item;
import se.iths.complexjavaproject.mudders.exception.BadDataException;

@Data
@AllArgsConstructor
public class ItemModel {

    private String name;
    private int value;

    public Item convertToEntity() throws BadDataException {

        if (this.getName() == null || this.getName().isBlank()) {
            throw new BadDataException("The name of the item is missing.");
        }

        if (this.getValue() < 0) {
            throw new BadDataException("The value can not be less than zero.");
        }

        return new Item(this.getName(), this.getValue());

    }

}
