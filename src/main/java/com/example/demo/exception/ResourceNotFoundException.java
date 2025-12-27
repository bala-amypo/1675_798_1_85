package com.example.demo.exception;

/**
 * Exception thrown when a requested resource (entity) is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    // Constructor with custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Optional: default constructor
    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
