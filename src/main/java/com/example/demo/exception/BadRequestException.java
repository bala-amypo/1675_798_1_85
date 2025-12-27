package com.example.demo.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    // Optional: include a default constructor
    public BadRequestException() {
        super("Bad request");
    }
}
