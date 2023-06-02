package com.carlosjr.security.employee;

import jakarta.persistence.*;

@Entity
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    boolean retired;

    public Employee(){}

    public Employee(Long id, String name, boolean retired){
        this.id = id;
        this.name = name;
        this.retired = retired;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}

