package com.example.carlosjr.ooconcepts.lifecycle;

public class RequestBean {

    private static Integer count = 0;

    public RequestBean() {
        this.count++;
    }

    public Integer getCount() {
        return count;
    }
}
