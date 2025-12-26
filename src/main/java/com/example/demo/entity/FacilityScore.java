package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "facility_scores", uniqueConstraints = {
        @UniqueConstraint(name = "uk_facilityscore_property", columnNames = "property_id")
})
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer schoolProximity;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer hospitalProximity;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer transportAccess;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer safetyScore;

    public FacilityScore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getSchoolProximity() {
        return schoolProximity;
    }

    public void setSchoolProximity(Integer schoolProximity) {
        this.schoolProximity = schoolProximity;
    }

    public Integer getHospitalProximity() {
        return hospitalProximity;
    }

    public void setHospitalProximity(Integer hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public Integer getTransportAccess() {
        return transportAccess;
    }

    public void setTransportAccess(Integer transportAccess) {
        this.transportAccess = transportAccess;
    }

    public Integer getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(Integer safetyScore) {
        this.safetyScore = safetyScore;
    }
}
