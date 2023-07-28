package com.example.carlosjr.netflux.bootstrap;

import com.example.carlosjr.netflux.domain.Movie;
import com.example.carlosjr.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
@RequiredArgsConstructor
public class InitMovies implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                .thenMany(Flux.just("Palmeiras o filme", "Filme do pelé", "Filme do Dudu", "Filme do veiga", "Palmeiras tri-libertadores")
                        .map(title -> Movie.builder().title(title).build())
                        .flatMap(movieRepository::save)).subscribe(null, null, () -> {
                            movieRepository.findAll().subscribe(System.out::println);
                        });
    }
}
