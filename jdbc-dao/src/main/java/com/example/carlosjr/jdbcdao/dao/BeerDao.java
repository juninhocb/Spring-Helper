package com.example.carlosjr.jdbcdao.dao;

import com.example.carlosjr.jdbcdao.domain.Beer;

public interface BeerDao {

    Beer getBeerById(Long id);

    Beer getBeerByIdPrepared(Long id);

    Beer saveNewBeer(Beer beer);

    Beer updateBeer(Beer beer);
    void deleteBeer(Long beerId);
}

