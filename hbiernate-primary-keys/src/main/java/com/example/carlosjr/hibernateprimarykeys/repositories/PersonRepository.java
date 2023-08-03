package com.example.carlosjr.hibernateprimarykeys.repositories;

import com.example.carlosjr.hibernateprimarykeys.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
