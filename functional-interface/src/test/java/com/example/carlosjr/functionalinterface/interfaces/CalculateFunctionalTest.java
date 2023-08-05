package com.example.carlosjr.functionalinterface.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateFunctionalTest {

    @Test
    void calculateTwoItemsInYourWay() {

        CalculateFunctional sumItems = (val1, val2) -> {
            return val1 + val2;
        };

        System.out.println(sumItems.calculateTwoItemsInYourWay(3,4));

        CalculateFunctional mulItems = (firstVal, secondVal) -> {
          return firstVal*secondVal;
        };

        System.out.println(mulItems.calculateTwoItemsInYourWay(2,2));



    }
}