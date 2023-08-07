package com.example.carlosjr.mockmvc.repositories;

import com.example.carlosjr.mockmvc.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
