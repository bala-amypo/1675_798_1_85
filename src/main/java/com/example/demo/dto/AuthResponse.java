package com.example.demo.dto;

public class AuthResponse {

    private String token;

    // Default constructor for Jackson
    public AuthResponse() {
    }

    // Constructor for easy creation
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter & Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
