package com.example.carlosjr.springwebclient.client;

import com.example.carlosjr.springwebclient.web.BeerDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BeerClient {

    Mono<BeerDto> getBeerById(Long id);

    Mono<BeerDto> getBeerByName(String name);
    Mono<BeerDto> getBeerByNameExampleWithNoRequiredParameter(String name);
    Mono<Long> createNewBeer();

    Mono<Long> createNewBeerWithBody(BeerDto beerDto);

    Mono<ResponseEntity<Void>> updateTest(BeerDto beerDto);

    Mono<ResponseEntity<Void>> deleteTest(Long id);

}
