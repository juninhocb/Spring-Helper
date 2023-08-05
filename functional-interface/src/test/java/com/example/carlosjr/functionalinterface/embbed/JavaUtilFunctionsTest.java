package com.example.carlosjr.functionalinterface.embbed;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class JavaUtilFunctionsTest {

    @Test
    void functionTest() {

        List<Integer> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);

        //functional that takes generic argument T and returns generic result R
        Function<List<Integer>, Integer> sumMyNumbers = list -> {
            return list.stream().reduce(0, Integer::sum);
        };

        System.out.println(sumMyNumbers.apply(listOfNumbers));

        Function<Integer, String> intToString = number -> "Number is: " + number;

        System.out.println(intToString.apply(3));

    }

    @Test
    void predicateTest() {

        //Predicate takes Generic T and returns boolean
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Verify if 3 is even, result is: " + isEven.test(3));
        System.out.println("Verify if 4 is even, result is: " + isEven.test(4));

    }

    @Test
    void consumerTest(){

        //Consumer takes Generic T and returns void
        Consumer<String> printer = string -> System.out.println("Print result: " + string);
        printer.accept("Welcome String");
        
    }

    @Test
    void supplierTest() {

        //Supplier takes nothing and returns Generic T
        Supplier<String> getString  = () -> {return "Welcome my friend";};

        System.out.println(getString.get());

    }

    @Test
    void unaryOperator() {
        //Unary takes a type T and returns the same type T
        UnaryOperator<Integer> square = num -> num * num;
        System.out.println(square.apply(5));
    }

    @Test
    void binaryOperator() {
        //The same but take two arguments
        BinaryOperator<Integer> subtract = (num1, num2) -> {
          return num1 - num2;
        };

        System.out.println("8 - 3 is equals to: " + subtract.apply(8,3));

    }
}
