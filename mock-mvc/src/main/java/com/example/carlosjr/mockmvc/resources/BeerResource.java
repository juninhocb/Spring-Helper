package com.example.carlosjr.mockmvc.resources;

import com.example.carlosjr.mockmvc.model.Beer;
import com.example.carlosjr.mockmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<Beer> getBeerById(@PathVariable(name = "beerId") Long id){
        return new ResponseEntity<>( beerService.getBeerById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Beer>> getAllBeers(){
        return ResponseEntity.ok().body(beerService.getAllBeers());
    }

    @PostMapping
    public ResponseEntity<Void> createNewBeer(@RequestBody Beer beer, UriComponentsBuilder ucb){
        Long beerId = beerService.createNewBeer(beer);

        URI resourcePath = ucb
                .path("/api/v1/beer/{id}")
                .buildAndExpand(beerId)
                .toUri();

        return ResponseEntity.created(resourcePath).build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "beerId") Long id){
        beerService.deleteBeer(id);
    }

}
