package com.example.springiojackson.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account{
    private Long id;
    private String firstName;
    private String secondName;
    private Address address;
    private final Date creationDate = new Date(System.currentTimeMillis());


    @JsonCreator
    public Account(Long id, String firstName, String secondName, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
    }
}


