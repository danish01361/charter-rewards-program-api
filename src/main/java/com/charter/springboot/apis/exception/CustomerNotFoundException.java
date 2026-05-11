package com.charter.springboot.apis.exception;

public class CustomerNotFoundException
        extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}