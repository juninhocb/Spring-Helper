package com.example.carlosjr.hibernateprimarykeys.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    /**
     * AUTO - JPA Provider (Such as hibernate) will generate the primary key value
     * IDENTITY - Database provider will do this
     * SEQUENCE - JPA uses database sequence to provide the primary key
     * TABLE - JPA uses a special table for storing keys (less efficient)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
