package com.example.carlos.basicmvckotlin.bootstrap

import com.example.carlos.basicmvckotlin.domain.entities.Car
import com.example.carlos.basicmvckotlin.services.CarService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MockData(private val carService: CarService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (carService.getRepositoryLength() == 0L){
            carService.createCars(getListOfCars())
        }
    }

    fun getListOfCars() : List<Car> {

        val c1 = Car();
        c1.name = "C-44"
        c1.model = "Ferrari"
        c1.color = "Red"
        c1.isOil = true
        c1.velocity = 400

        val c2 = Car();
        c2.name = "Fiat"
        c2.model = "Uno"
        c2.color = "Blue"
        c2.isOil = false
        c2.velocity = 125

        val c3 = Car();
        c3.name = "Ford"
        c3.model = "Fusion"
        c3.color = "White"
        c3.isOil = true
        c3.velocity = 200

        return listOf(c1,c2,c3)

    }
}