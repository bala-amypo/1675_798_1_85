package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Override
    public FacilityScore createScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        if (facilityScoreRepository.existsByProperty(property)) {
            throw new RuntimeException("Facility score already exists for this property");
        }
        
        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }
    
    @Override
    public FacilityScore getScoreByProperty(Property property) {
        return facilityScoreRepository.findByProperty(property).orElse(null);
    }
}