package com.example.carlosjr.mockmvc.bootstrap;

import com.example.carlosjr.mockmvc.model.Beer;
import com.example.carlosjr.mockmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockData implements CommandLineRunner {

    private final BeerService beerService;
    @Override
    public void run(String... args) throws Exception {

        loadBeers();
    }

    private void loadBeers() {

        Beer beer1 = Beer.builder().isAvailable(true).name("Kayser").quantityOnHand(13).build();
        Beer beer2 = Beer.builder().isAvailable(false).name("Heineken").quantityOnHand(0).build();
        Beer beer3 = Beer.builder().isAvailable(true).name("Amstel").quantityOnHand(18).build();

        beerService.createNewBeer(beer1);
        beerService.createNewBeer(beer2);
        beerService.createNewBeer(beer3);

    }
}
