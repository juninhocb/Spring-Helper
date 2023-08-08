package com.example.carlosjr.springwebfluxr2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    private Long id;
    private String name;
    private Integer age;
    @Column(value = "is_tall")
    private Boolean isTall;
    @CreatedDate
    @Column(value = "created_date")
    private LocalDateTime createdDate;

}
