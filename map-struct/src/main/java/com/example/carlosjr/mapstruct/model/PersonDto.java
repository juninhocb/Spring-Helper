package com.example.carlosjr.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private Long id;
    private String name;
    private Integer age;
    private Boolean stillAlive;
    private UUID document;
    private Address address;
}
