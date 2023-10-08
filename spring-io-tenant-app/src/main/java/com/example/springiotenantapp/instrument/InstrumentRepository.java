package com.example.springiotenantapp.instrument;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
    List<Instrument> findByType(String type);

}
