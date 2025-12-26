package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String title;
    
    @NotBlank
    @Column(nullable = false)
    private String address;
    
    @NotBlank
    @Column(nullable = false)
    private String city;
    
    @NotNull
    @Min(value = 1, message = "Price must be positive")
    @Column(nullable = false)
    private Double price;
    
    @NotNull
    @Min(value = 100, message = "Area must be at least 100 sq ft")
    @Column(nullable = false)
    private Double areaSqFt;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FacilityScore facilityScore;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RatingResult ratingResult;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RatingLog> ratingLogs = new ArrayList<>();
    
    @ManyToMany(mappedBy = "assignedProperties")
    private Set<User> assignedUsers = new HashSet<>();
    
    public Property() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Double getAreaSqFt() { return areaSqFt; }
    public void setAreaSqFt(Double areaSqFt) { this.areaSqFt = areaSqFt; }
    
    public FacilityScore getFacilityScore() { return facilityScore; }
    public void setFacilityScore(FacilityScore facilityScore) { this.facilityScore = facilityScore; }
    
    public RatingResult getRatingResult() { return ratingResult; }
    public void setRatingResult(RatingResult ratingResult) { this.ratingResult = ratingResult; }
    
    public List<RatingLog> getRatingLogs() { return ratingLogs; }
    public void setRatingLogs(List<RatingLog> ratingLogs) { this.ratingLogs = ratingLogs; }
    
    public Set<User> getAssignedUsers() { return assignedUsers; }
    public void setAssignedUsers(Set<User> assignedUsers) { this.assignedUsers = assignedUsers; }
    
    public void addRatingLog(RatingLog log) {
        ratingLogs.add(log);
        log.setProperty(this);
    }
}