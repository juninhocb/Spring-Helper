package com.example.carlosjr.mapstruct.repositories;

import com.example.carlosjr.mapstruct.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
