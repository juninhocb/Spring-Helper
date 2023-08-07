package com.example.carlosjr.mockmvc.services;

import com.example.carlosjr.mockmvc.model.Beer;
import com.example.carlosjr.mockmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    @Override
    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public Long createNewBeer(Beer beer) {
        Beer persistedBeer = beerRepository.save(beer);
        return persistedBeer.getId();
    }

    @Override
    public void deleteBeer(Long id) {
        beerRepository.delete(getBeerById(id));
    }
}
