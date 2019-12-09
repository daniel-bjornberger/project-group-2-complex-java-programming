package se.iths.complexjavaproject.mudders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidJsonDataException extends RuntimeException {

    private static final long serialVersionUID = 8005235881892117822L;

    public InvalidJsonDataException() {
        super("The json data is invalid.");
    }

}
