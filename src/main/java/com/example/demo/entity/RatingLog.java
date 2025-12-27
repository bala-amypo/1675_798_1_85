package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private Property property;

    public Long getId() { return `id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
}