package com.example.carlosjr.ooconcepts.lifecycle;

public class SingletonBean {

    private static Integer count = 0;

    public SingletonBean() {
        this.count++;
    }

    public Integer getCount() {
        return count;
    }
}
