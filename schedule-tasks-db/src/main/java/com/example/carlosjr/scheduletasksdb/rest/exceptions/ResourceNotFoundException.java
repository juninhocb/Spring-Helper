package com.example.carlosjr.scheduletasksdb.rest.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String idResource) {
        super(String.format("Resource %s was not found", idResource));
    }
}
