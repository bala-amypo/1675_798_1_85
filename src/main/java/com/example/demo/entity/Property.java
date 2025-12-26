package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private FacilityScore facilityScore;
    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RatingResult ratingResult;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>();
    
    @ManyToMany(mappedBy = "assignedProperties")
    private List<User> assignedUsers = new ArrayList<>();
    
    // Getters and Setters
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
    
    public List<User> getAssignedUsers() { return assignedUsers; }
    public void setAssignedUsers(List<User> assignedUsers) { this.assignedUsers = assignedUsers; }
    
    public void addRatingLog(RatingLog log) {
        ratingLogs.add(log);
        log.setProperty(this);
    }
}
