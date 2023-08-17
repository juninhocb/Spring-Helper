package com.example.carlosjr.scheduletasksdb.rest.exceptions;

import com.example.carlosjr.scheduletasksdb.rest.dtos.ExceptionMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessageDto> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){
        ExceptionMessageDto messageDto = getMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI(),
                e.getClass().toString()
        );
        return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(TimeFormatNotSupportedException.class)
    public ResponseEntity<ExceptionMessageDto> handleTimeFormatNotSupportedException(TimeFormatNotSupportedException e, HttpServletRequest request){
        ExceptionMessageDto messageDto = getMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI(),
                e.getClass().toString()
        );
        return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);

    }

    private ExceptionMessageDto getMessage(
                                           Integer statusCode,
                                           String message,
                                           String path,
                                           String javaClass){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        Date now = new Date();

        return ExceptionMessageDto.builder()
                .timestamp(simpleDateFormat.format(now))
                .message(message)
                .statusCode(statusCode)
                .path(path)
                .javaClass(javaClass)
                .build();

    }


}
