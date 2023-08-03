package com.example.carlosjr.hibernateprimarykeys.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class NameId implements Serializable {

    static final long serialVersionUID = 2708300709549117302L;

    private String firstName;
    private String lastName;
}
