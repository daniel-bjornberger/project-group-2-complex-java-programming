package se.iths.complexjavaproject.mudders.exception;

public class EmailExistsException extends Exception {
    private static final long serialVersionUID = 2718453335798281639L;

    public EmailExistsException(String message){
        super(message);
    }

}
