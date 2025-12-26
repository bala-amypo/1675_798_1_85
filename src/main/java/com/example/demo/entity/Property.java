package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
@Getter
@Setter
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String city;
    
    @NotNull
    @Min(0)
    private Double price;
    
    @NotNull
    @Min(0)
    private Double areaSqFt;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private FacilityScore facilityScore;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RatingResult ratingResult;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>();
    
    @ManyToMany(mappedBy = "assignedProperties")
    private List<User> assignedUsers = new ArrayList<>();
    
    public void addRatingLog(RatingLog log) {
        ratingLogs.add(log);
        log.setProperty(this);
    }
}
