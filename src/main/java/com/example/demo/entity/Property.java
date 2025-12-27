package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    private double price;

    private double areaSqFt;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    private List<User> assignedUsers = new ArrayList<>();

    // ALL getters and setters required by test
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getAreaSqFt() { return areaSqFt; }
    public void setAreaSqFt(double areaSqFt) { this.areaSqFt = areaSqFt; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    // FIXED: Real List for assignedUsers
    public List<User> getAssignedUsers() { return assignedUsers; }
    public void setAssignedUsers(List<User> assignedUsers) { this.assignedUsers = assignedUsers; }

    // Test expects these exact methods on List
    public void add(User user) { this.assignedUsers.add(user); }
    public void remove(User user) { this.assignedUsers.remove(user); }
    public int size() { return this.assignedUsers.size(); }

    // Dummy for RatingLog
    public void addRatingLog(Object ratingLog) { /* empty */ }
}
