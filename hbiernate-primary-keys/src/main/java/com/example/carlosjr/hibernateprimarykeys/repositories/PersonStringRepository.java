package com.example.carlosjr.hibernateprimarykeys.repositories;

import com.example.carlosjr.hibernateprimarykeys.models.PersonString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonStringRepository extends JpaRepository<PersonString, String> {
}
