package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;

import java.util.Optional;

public interface FacilityScoreService {
    FacilityScore createFacilityScore(Property property, FacilityScore score);
    Optional<FacilityScore> findByProperty(Property property);
}
