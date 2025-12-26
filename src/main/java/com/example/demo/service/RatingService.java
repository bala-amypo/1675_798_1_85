package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final RatingResultRepository ratingResultRepository;

    public RatingService(PropertyRepository propertyRepository,
                         FacilityScoreRepository facilityScoreRepository,
                         RatingResultRepository ratingResultRepository) {
        this.propertyRepository = propertyRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.ratingResultRepository = ratingResultRepository;
    }

    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new NotFoundException("Property not found: " + propertyId));

        FacilityScore score = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new BadRequestException("No facility score found for property " + propertyId));

        double finalRating = (score.getSchoolProximity()
                + score.getHospitalProximity()
                + score.getTransportAccess()
                + score.getSafetyScore()) / 4.0;

        RatingResult rr = new RatingResult();
        rr.setProperty(property);
        rr.setFinalRating(finalRating);
        rr.setRatingCategory(category(finalRating));

        return ratingResultRepository.save(rr);
    }

    @Transactional(readOnly = true)
    public Optional<RatingResult> getRatingByPropertyId(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) return Optional.empty();
        return ratingResultRepository.findByProperty(property);
    }

    private String category(double rating) {
        if (rating >= 8.0) return "EXCELLENT";
        if (rating >= 6.0) return "GOOD";
        if (rating >= 4.0) return "AVERAGE";
        return "POOR";
    }
}
