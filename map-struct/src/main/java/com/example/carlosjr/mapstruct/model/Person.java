package com.example.carlosjr.mapstruct.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private Boolean isAlive;
    private UUID document;
    @CreationTimestamp
    private Timestamp createdTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city", referencedColumnName = "city")
    private Address address;

}
