package com.Connectgame.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> responseUnprocessable (UnprocessableException exception){
        ErrorMessage errorMessage = new ErrorMessage(422,exception.getMessage(),new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> responseNotFound (NotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(404,exception.getMessage(),new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
