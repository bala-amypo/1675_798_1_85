package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "facility_scores", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"property_id"})
})
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "School proximity must be between 0 and 10")
    @Max(value = 10, message = "School proximity must be between 0 and 10")
    private int schoolProximity;

    @Min(value = 0, message = "Hospital proximity must be between 0 and 10")
    @Max(value = 10, message = "Hospital proximity must be between 0 and 10")
    private int hospitalProximity;

    @Min(value = 0, message = "Transport access must be between 0 and 10")
    @Max(value = 10, message = "Transport access must be between 0 and 10")
    private int transportAccess;

    @Min(value = 0, message = "Safety score must be between 0 and 10")
    @Max(value = 10, message = "Safety score must be between 0 and 10")
    private int safetyScore;

    @OneToOne(optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Getters & Setters
    public Long getId() { return id; }

    public int getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(int schoolProximity) { this.schoolProximity = schoolProximity; }

    public int getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(int hospitalProximity) { this.hospitalProximity = hospitalProximity; }

    public int getTransportAccess() { return transportAccess; }
    public void setTransportAccess(int transportAccess) { this.transportAccess = transportAccess; }

    public int getSafetyScore() { return safetyScore; }
    public void setSafetyScore(int safetyScore) { this.safetyScore = safetyScore; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
}
