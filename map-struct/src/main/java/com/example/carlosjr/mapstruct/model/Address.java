package com.example.carlosjr.mapstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    private String province;
    @Id
    @Column(unique = true)
    private String city;
    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Person> personList;
}
