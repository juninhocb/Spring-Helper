package com.example.carlosjr.cachevalidatemapper.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionEnhanceDto> genericExceptionHandler(Exception ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getEnhancedResponse(
                        ex.getMessage(),
                        request.getRequestURI(),
                        ex.getClass().getName()));

    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ExceptionEnhanceDto> handleTeamNotFound(TeamNotFoundException ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(getEnhancedResponse(
                        ex.getMessage(),
                        request.getRequestURI(),
                        ex.getClass().getName()));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ExceptionEnhanceDto> handleMethodNotAllowed(MethodNotAllowedException ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(getEnhancedResponse(
                        ex.getMessage(),
                        request.getRequestURI(),
                        ex.getClass().getName()));
    }

    private ExceptionEnhanceDto getEnhancedResponse(String msg, String path, String className){
        return ExceptionEnhanceDto.builder()
                .message(msg)
                .path(path)
                .className(className)
                .timestamp(LocalDateTime.now())
                .build();
    }



}
