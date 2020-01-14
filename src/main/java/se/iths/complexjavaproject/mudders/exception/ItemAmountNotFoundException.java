package se.iths.complexjavaproject.mudders.exception;

public class ItemAmountNotFoundException extends Exception {

    private static final long serialVersionUID = 4669409001280714454L;

    public ItemAmountNotFoundException(String characterName, String itemName) {
        super(characterName + " doesn't have any '" + itemName + "' items.");
    }

}
