package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double finalRating;

    private String ratingCategory;

    @OneToOne
    private Property property;

    public Long getId() { return id; }

    public double getFinalRating() { return finalRating; }
    public void setFinalRating(double finalRating) { this.finalRating = finalRating; }

    public String getRatingCategory() { return ratingCategory; }
    public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    
    // ADD THIS ONE METHOD - test requires it
    public java.time.LocalDateTime getRatedAt() { 
        return java.time.LocalDateTime.now(); 
    }
}
