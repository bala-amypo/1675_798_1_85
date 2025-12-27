package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0) @Max(10)
    private int schoolProximity;

    @Min(0) @Max(10)
    private int hospitalProximity;

    @Min(0) @Max(10)
    private int transportAccess;

    @Min(0) @Max(10)
    private int safetyScore;

    @OneToOne
    private Property property;

    public Long getId() { return id; }

    public int getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(int v) { this.schoolProximity = v; }

    public int getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(int v) { this.hospitalProximity = v; }

    public int getTransportAccess() { return transportAccess; }
    public void setTransportAccess(int v) { this.transportAccess = v; }

    public int getSafetyScore() { return safetyScore; }
    public void setSafetyScore(int v) { this.safetyScore = v; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
}