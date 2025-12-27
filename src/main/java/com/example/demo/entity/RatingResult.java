package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating_results")
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double finalRating;

    private String ratingCategory;

    @OneToOne(optional = false)
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    @Column(updatable = false)
    private LocalDateTime ratedAt;

    @PrePersist
    protected void onCreate() {
        this.ratedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }

    public double getFinalRating() { return finalRating; }
    public void setFinalRating(double finalRating) { this.finalRating = finalRating; }

    public String getRatingCategory() { return ratingCategory; }
    public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }

    // Required by tests
    public LocalDateTime getRatedAt() { return ratedAt; }
}
