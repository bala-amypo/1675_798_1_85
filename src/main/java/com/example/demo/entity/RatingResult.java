package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double finalRating;
    private String ratingCategory;

    // 🔴 ADD THIS (tests expect getRatedAt)
    private LocalDateTime ratedAt;

    @OneToOne
    private Property property;

    public Long getId() { return id; }

    public double getFinalRating() { return finalRating; }
    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
    }

    public String getRatingCategory() { return ratingCategory; }
    public void setRatingCategory(String ratingCategory) {
        this.ratingCategory = ratingCategory;
    }

    // 🔴 REQUIRED BY TEST
    public LocalDateTime getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(LocalDateTime ratedAt) {
        this.ratedAt = ratedAt;
    }

    public Property getProperty() { return property; }
    public void setProperty(Property property) {
        this.property = property;
    }
}
