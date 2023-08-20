package com.example.carlos.basicmvckotlin.services

import com.example.carlos.basicmvckotlin.domain.entities.Car
import com.example.carlos.basicmvckotlin.exceptions.ResourceNotFoundException
import com.example.carlos.basicmvckotlin.repositories.CarRepository
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarServiceImpl(private val carRepository: CarRepository) : CarService {
    override fun getRepositoryLength(): Long {
        return carRepository.count()
    }

    override fun findCarById(id: Int): Car {
        val carOptional: Optional<Car> = carRepository.findById(id)
        if (carOptional.isEmpty){
            throw ResourceNotFoundException("Id: $id")
        }
        return carOptional.get()
    }

    override fun findCarByName(name: String): Car {
        val carOptional: Optional<Car> = carRepository.findByName(name)
        if (carOptional.isEmpty){
            throw ResourceNotFoundException("Name: $name")
        }
        return carOptional.get()
    }

    override fun findAllCars(pageAble: Pageable): Set<Car> {
        return carRepository.findAll(pageAble).toSet()
    }

    override fun createNewCar(car: Car): Int? {
        val persistedCar = carRepository.save(car)
        return persistedCar.id
    }
    override fun createCars(cars: List<Car>) {
        carRepository.saveAll(cars)
    }

    override fun updateCar(id: Int, car: Car) {
        val persistedCar = findCarById(id)
        val id = persistedCar.id
        val createdAt = persistedCar.createdTime
        BeanUtils.copyProperties(car, persistedCar)
        persistedCar.id = id
        persistedCar.createdTime = createdAt
        carRepository.save(persistedCar)
    }

    override fun deleteCar(id: Int) {
        val persistedCar = findCarById(id)
        carRepository.delete(persistedCar)
    }
}