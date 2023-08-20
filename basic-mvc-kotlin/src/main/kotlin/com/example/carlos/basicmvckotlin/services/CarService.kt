package com.example.carlos.basicmvckotlin.services

import com.example.carlos.basicmvckotlin.domain.entities.Car
import org.springframework.data.domain.Pageable

interface CarService {
    fun getRepositoryLength() : Long
    fun findCarById(id: Int): Car
    fun findCarByName(name: String): Car
    fun findAllCars(pageAble: Pageable): Set<Car>
    fun createNewCar(car: Car): Int?
    fun createCars (cars: List<Car>)
    fun updateCar(id: Int, car: Car)
    fun deleteCar(id: Int)


}