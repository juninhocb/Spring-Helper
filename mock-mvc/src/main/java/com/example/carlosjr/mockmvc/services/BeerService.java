package com.example.carlosjr.mockmvc.services;

import com.example.carlosjr.mockmvc.model.Beer;

import java.util.List;

public interface BeerService {

    Beer getBeerById(Long id);
    List<Beer> getAllBeers();
    Long createNewBeer(Beer beer);
    void deleteBeer(Long id);

}
