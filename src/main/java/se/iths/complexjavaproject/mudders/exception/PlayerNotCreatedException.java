package se.iths.complexjavaproject.mudders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlayerNotCreatedException extends RuntimeException {

    private static final long serialVersionUID = 5887111575817154641L;

    public PlayerNotCreatedException(String exception){
        super(exception);
    }

}

