package com.example.webfluxdemo.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClosuresStubTest {

    @Test
    public void lambdaExample(){

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        numbers.stream()
                .map(item -> (item * 2) + " " )
                .forEach(System.out::print);
    }

    @Test
    public void closureExample(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Integer multiplier = 2;
        numbers.stream()
                .map(item -> (item * multiplier) + " " )
                .forEach(System.out::print);
    }

    @Test
    public void closureExampleUsingFinal(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        final Integer multiplier = 2;
        numbers.stream()
                .map(item -> (item * multiplier) + " " )
                .forEach(System.out::print);
        //multiplier = 3;

    }

    @Test
    public void breakingFinal (){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        final Integer[] multiplier = {2};

        //Lazy load
        Stream<Integer> streamOfMyNumbers = numbers.stream()
                .map(item -> item * multiplier[0]);
        multiplier[0] = 0;

        //this calls the anonymous function by lazy
        streamOfMyNumbers.forEach(System.out::println);

    }

}
