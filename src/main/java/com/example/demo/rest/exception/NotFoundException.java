package com.example.demo.rest.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}