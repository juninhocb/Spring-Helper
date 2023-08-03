package com.example.carlosjr.jdbcdao.dao;

import com.example.carlosjr.jdbcdao.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.example.carlosjr.jdbcdao.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BeerDaoImplTest {

    @Autowired
    BeerDaoImpl beerDao;

    @Test
    void shouldGetBeerById() {

        Beer persistedBeer = beerDao.getBeerById(1L);

        assertThat(persistedBeer).isNotNull();

        System.out.println(persistedBeer);

    }

    @Test
    void shouldGetBeerByIdPrepared() {

        Beer persistedBeer = beerDao.getBeerByIdPrepared(1L);

        assertThat(persistedBeer).isNotNull();

        System.out.println(persistedBeer);

    }

    @Test
    @DirtiesContext
    void shouldSaveAnEntity() {

        Beer beerSaved = beerDao.saveNewBeer(Beer.builder().name("Bierland").description("Great beer from Blumenau-SC").build());

        assertThat(beerSaved).isNotNull();

        System.out.println(beerSaved);

    }

    @Test
    @DirtiesContext
    void shouldUpdateAnEntity() {

        Beer beerSaved = beerDao.saveNewBeer(Beer.builder().name("Devassa").description("Good bear").build());

        beerSaved.setDescription("Good bear from i dont know where");

        Beer beerUpdated = beerDao.updateBeer(beerSaved);

        assertThat(beerUpdated.getDescription()).isEqualTo("Good bear from i dont know where");

        System.out.println(beerUpdated);

    }

    @Test
    @DirtiesContext
    void shouldDeleteAnEntity() {

        Beer beerSaved = beerDao.saveNewBeer(Beer.builder().name("Devassa").description("Good bear").build());

        beerSaved.setDescription("Good bear from i dont know where");

        beerDao.deleteBeer(beerSaved.getId());

        assertThat(beerDao.getBeerByIdPrepared(beerSaved.getId())).isNull();

    }
}