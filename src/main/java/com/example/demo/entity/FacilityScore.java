package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "facility_scores", uniqueConstraints = @UniqueConstraint(columnNames = "property_id"))
public class FacilityScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    
    @Min(1)
    @Max(10)
    private Integer schoolProximity;
    
    @Min(1)
    @Max(10)
    private Integer hospitalProximity;
    
    @Min(1)
    @Max(10)
    private Integer transportAccess;
    
    @Min(1)
    @Max(10)
    private Integer safetyScore;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    
    public Integer getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(Integer schoolProximity) { this.schoolProximity = schoolProximity; }
    
    public Integer getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(Integer hospitalProximity) { this.hospitalProximity = hospitalProximity; }
    
    public Integer getTransportAccess() { return transportAccess; }
    public void setTransportAccess(Integer transportAccess) { this.transportAccess = transportAccess; }
    
    public Integer getSafetyScore() { return safetyScore; }
    public void setSafetyScore(Integer safetyScore) { this.safetyScore = safetyScore; }
}
