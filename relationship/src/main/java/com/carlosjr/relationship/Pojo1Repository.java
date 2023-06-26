package com.carlosjr.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Pojo1Repository extends JpaRepository<Pojo1, UUID> {
}
