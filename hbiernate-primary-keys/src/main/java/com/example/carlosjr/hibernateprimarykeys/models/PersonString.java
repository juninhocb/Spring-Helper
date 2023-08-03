package com.example.carlosjr.hibernateprimarykeys.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class PersonString {

    @Id
    @Column(columnDefinition = "VARCHAR(12)", length = 12)
    private String upc; //unique person code
    private String name;

}
