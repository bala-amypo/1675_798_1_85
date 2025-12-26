package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private FacilityScore facilityScore;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_property",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignedUsers = new HashSet<>();

    // Getters and setters
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
        this.ratingLogs.add(log);
    }
}
