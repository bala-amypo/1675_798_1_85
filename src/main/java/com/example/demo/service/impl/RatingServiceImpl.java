package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository fsRepository;
    private final RatingResultRepository ratingResultRepository;
    private final RatingLogRepository logRepository;

    public RatingServiceImpl(PropertyRepository propertyRepository,
                             FacilityScoreRepository fsRepository,
                             RatingResultRepository ratingResultRepository,
                             RatingLogRepository logRepository) {
        this.propertyRepository = propertyRepository;
        this.fsRepository = fsRepository;
        this.ratingResultRepository = ratingResultRepository;
        this.logRepository = logRepository;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Property not found with ID: " + propertyId));

        FacilityScore facilityScore = fsRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "FacilityScore not found for property ID: " + propertyId));

        // Calculate average
        double avg = (facilityScore.getSchoolProximity()
                    + facilityScore.getHospitalProximity()
                    + facilityScore.getTransportAccess()
                    + facilityScore.getSafetyScore()) / 4.0;

        // Determine category
        String category;
        if (avg < 4) category = "POOR";
        else if (avg < 6) category = "AVERAGE";
        else if (avg < 8) category = "GOOD";
        else category = "EXCELLENT";

        // Save rating result
        RatingResult ratingResult = new RatingResult();
        ratingResult.setProperty(property);
        ratingResult.setFinalRating(avg);
        ratingResult.setRatingCategory(category);
        ratingResult = ratingResultRepository.save(ratingResult);

        // Log the rating
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage("Rating generated: " + category);
        logRepository.save(log);

        return ratingResult;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Property not found with ID: " + propertyId));

        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "RatingResult not found for property ID: " + propertyId));
    }
}
