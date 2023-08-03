package com.example.carlosjr.hibernateprimarykeys.repositories;

import com.example.carlosjr.hibernateprimarykeys.models.PersonUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonUuidRepository extends JpaRepository<PersonUuid, UUID> {
}
