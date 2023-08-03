package com.example.carlosjr.hibernateprimarykeys.repositories;

import com.example.carlosjr.hibernateprimarykeys.models.NameId;
import com.example.carlosjr.hibernateprimarykeys.models.PersonComposite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonCompositeRepository extends JpaRepository<PersonComposite, NameId> {
}
