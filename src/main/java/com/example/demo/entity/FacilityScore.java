package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(
        name = "facility_scores",
        uniqueConstraints = @UniqueConstraint(columnNames = "property_id")
)
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    @Min(0) @Max(10)
    private int schoolProximity;

    @Min(0) @Max(10)
    private int hospitalProximity;

    @Min(0) @Max(10)
    private int transportAccess;

    @Min(0) @Max(10)
    private int safetyScore;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }

    public int getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(int schoolProximity) { this.schoolProximity = schoolProximity; }

    public int getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(int hospitalProximity) { this.hospitalProximity = hospitalProximity; }

    public int getTransportAccess() { return transportAccess; }
    public void setTransportAccess(int transportAccess) { this.transportAccess = transportAccess; }

    public int getSafetyScore() { return safetyScore; }
    public void setSafetyScore(int safetyScore) { this.safetyScore = safetyScore; }
}
