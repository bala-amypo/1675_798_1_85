package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    public RatingResult generateRating(Property property) {
        FacilityScore score = facilityScoreRepository.findByProperty(property)
            .orElseThrow(() -> new RuntimeException("Facility score not found"));
        
        double avgScore = (score.getSchoolProximity() + score.getHospitalProximity() + 
                          score.getTransportAccess() + score.getSafetyScore()) / 4.0;
        
        String category;
        if (avgScore >= 8) {
            category = "EXCELLENT";
        } else if (avgScore >= 6) {
            category = "GOOD";
        } else if (avgScore >= 4) {
            category = "AVERAGE";
        } else {
            category = "POOR";
        }
        
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avgScore);
        result.setRatingCategory(category);
        
        return ratingResultRepository.save(result);
    }
}