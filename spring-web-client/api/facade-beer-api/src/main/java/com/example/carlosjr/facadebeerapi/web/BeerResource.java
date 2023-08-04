package com.example.carlosjr.facadebeerapi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/beer")
public class BeerResource {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable(name = "beerId")Long id){
        BeerDto beerDto = BeerDto.builder()
                .id(id)
                .lastUpdatedDate(new Date())
                .upc("KDGJF12343L")
                .createdDate(new Date())
                .beerName("Kayser")
                .beerStyle("Lagger")
                .quantityOnHand(13)
                .price(new BigDecimal("2.70"))
                .build();
        return ResponseEntity.ok().body(beerDto);
    }

    @GetMapping
    public ResponseEntity<BeerDto> getBeerByName(@RequestParam(name = "beerName") String name){
        BeerDto beerDto = BeerDto.builder()
                .id(2L)
                .lastUpdatedDate(new Date())
                .upc("PQSJF12343L")
                .createdDate(new Date())
                .beerName(name)
                .beerStyle("IPA")
                .quantityOnHand(13)
                .price(new BigDecimal("4.70"))
                .build();
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createNewBeer(){
        return ResponseEntity.ok(new Random().nextLong(20L));
    }
    @PostMapping("/with-body")
    public ResponseEntity<Long> createNewBeerWithBody(@RequestBody BeerDto beerDto){
        System.out.println("New body comes: " + beerDto);
        return ResponseEntity.ok(new Random().nextLong(20L));
    }

    @PutMapping("/update-body")
    public ResponseEntity<Void> updateTest(@RequestBody BeerDto beerDto){
        System.out.println("New body comes update: " + beerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable(name = "id") Long id){
        System.out.println("New id to delete comes: " + id);
        return ResponseEntity.noContent().build();
    }
}
