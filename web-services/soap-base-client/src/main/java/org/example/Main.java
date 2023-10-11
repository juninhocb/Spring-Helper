package org.example;

import org.example.converter.MyParser;
import org.example.model.Stub;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Stub stub = new Stub(1, "John", false);
        System.out.println(Arrays.toString(MyParser.parseObjectToBytes(stub)));
        //System.out.println("Hello world!");
    }
}