package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    @Override
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        FacilityScore score = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new RuntimeException("Facility score not found"));
        
        double avgScore = (score.getSchoolProximity() + score.getHospitalProximity() + 
                          score.getTransportAccess() + score.getSafetyScore()) / 4.0;
        
        String category;
        if (avgScore >= 8.5) category = "EXCELLENT";
        else if (avgScore >= 7.0) category = "GOOD";
        else if (avgScore >= 5.0) category = "AVERAGE";
        else category = "POOR";
        
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avgScore);
        result.setRatingCategory(category);
        
        return ratingResultRepository.save(result);
    }
    
    @Override
    public RatingResult getRatingByPropertyId(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return ratingResultRepository.findByProperty(property).orElse(null);
    }
}