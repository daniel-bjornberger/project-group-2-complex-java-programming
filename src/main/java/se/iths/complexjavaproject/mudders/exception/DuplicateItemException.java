package se.iths.complexjavaproject.mudders.exception;

public class DuplicateItemException extends Exception {

    private static final long serialVersionUID = 626181363636915488L;

    public DuplicateItemException(String itemName) {
        super("An item with the name '" + itemName + "' is already stored in the database");
    }

}
