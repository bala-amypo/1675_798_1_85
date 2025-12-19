package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    @Min(0)
    @Max(10)
    private Integer schoolProximity;

    @Min(0)
    @Max(10)
    private Integer hospitalProximity;

    @Min(0)
    @Max(10)
    private Integer transportAccess;

    @Min(0)
    @Max(10)
    private Integer safetyScore;

    public FacilityScore() {
    }

    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public Integer getSchoolProximity() {
        return schoolProximity;
    }

    public Integer getHospitalProximity() {
        return hospitalProximity;
    }

    public Integer getTransportAccess() {
        return transportAccess;
    }

    public Integer getSafetyScore() {
        return safetyScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setSchoolProximity(Integer schoolProximity) {
        this.schoolProximity = schoolProximity;
    }

    public void setHospitalProximity(Integer hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public void setTransportAccess(Integer transportAccess) {
        this.transportAccess = transportAccess;
    }

    public void setSafetyScore(Integer safetyScore) {
        this.safetyScore = safetyScore;
    }
}
