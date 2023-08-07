package com.example.carlosjr.mockmvc.resources;

import com.example.carlosjr.mockmvc.model.Beer;
import com.example.carlosjr.mockmvc.repositories.BeerRepository;
import com.example.carlosjr.mockmvc.services.BeerService;
import com.example.carlosjr.mockmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest
class BeerResourceTest {


    BeerServiceImpl beerServiceImpl;

    BeerService beerService;
    @Mock
    BeerRepository beerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        beerService = new BeerServiceImpl(beerRepository);
    }

    @Test
    void getAll() {

        Beer beer = Beer.builder().name("Skol").isAvailable(true).quantityOnHand(10).build();
        List<Beer> beers = new ArrayList<>();
        beers.add(beer);

        when(beerService.getAllBeers()).thenReturn(beers);

        List<Beer> beerListFromService = beerService.getAllBeers();
        assertEquals(beerListFromService.size(),  1);
        verify(beerService, times(1)).getAllBeers();


    }

    @Test
    void getBeerById() throws Exception {

        createBeer();

        Beer testBeer = beerServiceImpl.getAllBeers().get(0);

        given(beerServiceImpl.getBeerById(any(Long.class))).willReturn(testBeer);

        /*mockMvc.perform(get("/api/v1/beer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));*/


    }

    private void createBeer() {
        Beer beer = Beer.builder().name("Skol").isAvailable(true).quantityOnHand(10).build();
        beerServiceImpl.createNewBeer(beer);
    }
}