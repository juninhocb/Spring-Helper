package com.example.carlosjr.scheduletasksdb.rest.exceptions;

public class TimeFormatNotSupportedException extends RuntimeException{
    public TimeFormatNotSupportedException(String time) {
        super(String.format("This time %s is not formatted properly. The correct pattern is HH/mm/ss", time));
    }
}
