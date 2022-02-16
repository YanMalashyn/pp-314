package com.example.springcrudsecurityboot.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<UserIncorrectData> handleException(NoSuchUserIdException noSuchUserException){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(noSuchUserException.getLocalizedMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserIncorrectData> handleException(Exception exception){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getLocalizedMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
