package com.example.carlosjr.springwebmvc.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> getGenericException(Exception ex){
        return new ResponseEntity<>("Generic exception handler", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
