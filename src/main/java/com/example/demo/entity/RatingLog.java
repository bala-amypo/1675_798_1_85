package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String message;

    private LocalDateTime loggedAt;

    @PrePersist
    public void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }

    public RatingLog() {}

    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
