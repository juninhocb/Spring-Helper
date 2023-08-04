package com.example.carlosjr.ooconcepts.lifecycle;

public class PrototypeBean {

    private static Integer count = 0;

    public PrototypeBean() {
        this.count++;
    }

    public Integer getCount() {
        return count;
    }
}
