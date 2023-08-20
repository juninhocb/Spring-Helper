package com.example.carlos.basicmvckotlin.rest

import com.example.carlos.basicmvckotlin.domain.entities.Car
import com.example.carlos.basicmvckotlin.services.CarService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/v1/car")
class CarController(private val carService: CarService) {

    @GetMapping("/{carId}")
    fun getCarById(@PathVariable(name = "carId") id: Int) : ResponseEntity<Car>{
        val car = carService.findCarById(id)
        return handleCarResponse(car)
    }

    @GetMapping("/find")
    fun getCarByName(@RequestParam(name = "name") name: String) : ResponseEntity<Car>{
        val car = carService.findCarByName(name)
        return handleCarResponse(car)
    }

    @GetMapping
    fun findAllCars(pageAble: Pageable) : ResponseEntity<Set<Car>> {
        val cars = carService.findAllCars(PageRequest.of(
                pageAble.pageNumber,
                pageAble.pageSize,
                Sort.by(Sort.Direction.ASC, "id")
        ))
        return ResponseEntity.ok().body(cars)
    }

    @PostMapping
    fun createCar(@RequestBody car:Car, ucb: UriComponentsBuilder) : ResponseEntity<Void>{
        val carId = carService.createNewCar(car)
        val resourcePath: URI = ucb
                .path("/api/v1/car/{id}")
                .buildAndExpand(carId)
                .toUri()
        return ResponseEntity.created(resourcePath).build()
    }

    @PutMapping("/{carId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCar(@RequestBody car:Car, @PathVariable(name = "carId") id: Int){
        carService.updateCar(id, car)
    }

    @DeleteMapping("/{carId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCar(@PathVariable carId: Int) {
        carService.deleteCar(carId)
    }

    fun handleCarResponse(car: Car) : ResponseEntity<Car>{
        return if ( car != null){
            ResponseEntity.ok().body(car)
        } else {
            ResponseEntity.notFound().build()
        }
    }

}