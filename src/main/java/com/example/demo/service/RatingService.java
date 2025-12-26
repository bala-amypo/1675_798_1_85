package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RatingService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
            .orElseThrow(() -> new RuntimeException("Property not found"));
            
        FacilityScore score = facilityScoreRepository.findByProperty(property)
            .orElseThrow(() -> new RuntimeException("No facility score found"));
        
        double finalRating = (score.getSchoolProximity() + score.getHospitalProximity() + 
                            score.getTransportAccess() + score.getSafetyScore()) / 4.0;
        
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(getRatingCategory(finalRating));
        result.setRatedAt(java.time.LocalDateTime.now());
        
        return ratingResultRepository.save(result);
    }
    
    private String getRatingCategory(double rating) {
        if (rating >= 8.0) return "EXCELLENT";
        if (rating >= 6.0) return "GOOD";
        if (rating >= 4.0) return "AVERAGE";
        return "POOR";
    }
    
    public Optional<RatingResult> getRatingByPropertyId(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) return Optional.empty();
        return ratingResultRepository.findByProperty(property);
    }
}
