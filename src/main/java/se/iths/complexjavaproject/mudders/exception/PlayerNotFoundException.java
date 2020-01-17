package se.iths.complexjavaproject.mudders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException  {

    private static final long serialVersionUID = 8622478039709490666L;

    public PlayerNotFoundException(String exception) {
        super(exception);
        System.out.println("Player could not be found!" + "\nCheck database.");
    }



}
