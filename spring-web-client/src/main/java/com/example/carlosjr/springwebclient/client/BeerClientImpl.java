package com.example.carlosjr.springwebclient.client;

import com.example.carlosjr.springwebclient.web.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;
    @Override
    public Mono<BeerDto> getBeerById(Long id) {
        return webClient
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(BeerDto.class);
    }

    @Override
    public Mono<BeerDto> getBeerByName(String name) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("beerName", name).build())
                .retrieve()
                .bodyToMono(BeerDto.class);
    }

    @Override
    public Mono<BeerDto> getBeerByNameExampleWithNoRequiredParameter(String name) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.queryParamIfPresent("beerName", Optional.ofNullable(name)).build())
                .retrieve()
                .bodyToMono(BeerDto.class);
    }


    @Override
    public Mono<Long> createNewBeer() {
        return webClient
                .post()
                .retrieve()
                .bodyToMono(Long.class);
    }

    @Override
    public Mono<Long> createNewBeerWithBody(BeerDto beerDto) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/with-body").build())
                .body(BodyInserters.fromValue(beerDto))
                .retrieve()
                .bodyToMono(Long.class);
    }

    @Override
    public Mono<ResponseEntity<Void>> updateTest(BeerDto beerDto) {
        return webClient
                .put()
                .uri(uriBuilder -> uriBuilder.path("/update-body").build())
                .body(BodyInserters.fromValue(beerDto))
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTest(Long id) {
        return webClient
                .delete()
                .uri(uriBuilder -> uriBuilder.path("/" + id).build())
                .retrieve()
                .toBodilessEntity();
    }
}
