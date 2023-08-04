package com.example.carlosjr.springwebclient.client;

import com.example.carlosjr.springwebclient.web.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void getBeerById() {

        Mono<BeerDto> beerDtoMono = beerClient.getBeerById(12L);

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto).isNotNull();

        System.out.println(beerDto);

    }

    @Test
    void getBeerByName() {

        Mono<BeerDto> beerDtoMono = beerClient.getBeerByName("Bierland");

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto).isNotNull();

        System.out.println(beerDto);

    }

    @Test
    void getBeerByNameWhenNotRequired() {

        Mono<BeerDto> beerDtoMono = beerClient.getBeerByNameExampleWithNoRequiredParameter("");

        BeerDto beerDto = beerDtoMono.block();

        assertThat(beerDto).isNotNull();

        System.out.println(beerDto);

    }

    @Test
    void createNewBeer() {

        Mono<Long> getBeerId = beerClient.createNewBeer();

        assertThat(getBeerId).isNotNull();

        Long beerId = getBeerId.block();

        System.out.println(beerId);

    }

    @Test
    void createNewBeerWithBody() {

        BeerDto beerDto = BeerDto.builder()
                .id(3L)
                .lastUpdatedDate(new Date())
                .upc("ANCJF12343L")
                .createdDate(new Date())
                .beerName("Amstel")
                .beerStyle("Lagger")
                .quantityOnHand(18)
                .price(new BigDecimal("7.70"))
                .build();

        Mono<Long> getBeerId = beerClient.createNewBeerWithBody(beerDto);

        assertThat(getBeerId).isNotNull();

        Long beerId = getBeerId.block();

        System.out.println(beerId);

    }

    @Test
    void updateTest(){
        BeerDto beerDto = BeerDto.builder()
                .id(4L)
                .lastUpdatedDate(new Date())
                .upc("POWJF12343L")
                .createdDate(new Date())
                .beerName("Devassa")
                .beerStyle("IPA")
                .quantityOnHand(14)
                .price(new BigDecimal("2.70"))
                .build();

        Mono<ResponseEntity<Void>> responseVoid = beerClient.updateTest(beerDto);

        ResponseEntity<Void> responseEntity = responseVoid.block();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteTest(){

        Mono<ResponseEntity<Void>> responseVoid = beerClient.deleteTest(8L);

        ResponseEntity<Void> responseEntity = responseVoid.block();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}