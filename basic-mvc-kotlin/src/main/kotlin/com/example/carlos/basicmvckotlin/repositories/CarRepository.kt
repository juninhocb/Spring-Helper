package com.example.carlos.basicmvckotlin.repositories

import com.example.carlos.basicmvckotlin.domain.entities.Car
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarRepository : JpaRepository<Car, Int> {

    fun findByName(name: String): Optional<Car>

}