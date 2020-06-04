package com.api.personrest.exception;

public class PersonException extends RuntimeException{

    public PersonException(String message) {
        super(message);
    }

    public PersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
