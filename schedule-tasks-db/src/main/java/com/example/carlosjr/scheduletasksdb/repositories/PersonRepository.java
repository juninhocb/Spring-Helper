package com.example.carlosjr.scheduletasksdb.repositories;

import com.example.carlosjr.scheduletasksdb.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Person p SET p.scheduleTime = :scheduleTime WHERE p.id = :id")
    void updateScheduleTimeById(@Param("id") Long id, @Param("scheduleTime") LocalTime scheduleTime);

}
