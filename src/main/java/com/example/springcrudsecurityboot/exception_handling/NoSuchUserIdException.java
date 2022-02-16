package com.example.springcrudsecurityboot.exception_handling;

public class NoSuchUserIdException extends RuntimeException {

    public NoSuchUserIdException(String message) {
        super(message);
    }
}
