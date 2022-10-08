package com.example.demo.rest.exception;

public class PurchaseNotFound extends RuntimeException{
    public PurchaseNotFound() {
    }

    public PurchaseNotFound(String message) {
        super(message);
    }
}
