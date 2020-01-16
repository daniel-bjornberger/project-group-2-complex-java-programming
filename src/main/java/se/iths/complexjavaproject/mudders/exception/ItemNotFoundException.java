package se.iths.complexjavaproject.mudders.exception;

public class ItemNotFoundException extends Exception {

    private static final long serialVersionUID = 6365525768057292964L;

    public ItemNotFoundException(String itemName) {
        super("An item with the name '" + itemName + "' can not be found in the database");
    }

}
