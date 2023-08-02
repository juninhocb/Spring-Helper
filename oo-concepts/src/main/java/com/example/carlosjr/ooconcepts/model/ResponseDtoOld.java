package com.example.carlosjr.ooconcepts.model;

public class ResponseDtoOld {

    private String identity;
    private String message;

    public ResponseDtoOld() {
    }

    public ResponseDtoOld(String identity, String message) {
        this.identity = identity;
        this.message = message;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
