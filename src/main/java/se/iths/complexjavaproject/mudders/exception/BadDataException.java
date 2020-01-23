package se.iths.complexjavaproject.mudders.exception;

public class BadDataException extends Exception {
    private static final long serialVersionUID = 2797821045919580628L;

    public BadDataException(String message) {
        super(message);
    }
}
