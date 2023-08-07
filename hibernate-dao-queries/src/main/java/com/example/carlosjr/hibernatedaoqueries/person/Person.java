package com.example.carlosjr.hibernatedaoqueries.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQueries({
        @NamedQuery(name = "find_all", query = "FROM Person "),
        @NamedQuery(name = "find_by_name", query = "FROM Person p WHERE p.name = :name")
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isAlive;
    private Integer age;

}
