package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RatingServiceImpl implements RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final RatingResultRepository ratingResultRepository;
    private final RatingLogRepository ratingLogRepository;
    private final Validator validator;

    public RatingServiceImpl(PropertyRepository propertyRepository,
                             FacilityScoreRepository facilityScoreRepository,
                             RatingResultRepository ratingResultRepository,
                             RatingLogRepository ratingLogRepository,
                             Validator validator) {
        this.propertyRepository = propertyRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.ratingResultRepository = ratingResultRepository;
        this.ratingLogRepository = ratingLogRepository;
        this.validator = validator;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        FacilityScore fs = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("FacilityScore not found for property id: " + propertyId));

        double avg = (fs.getSchoolProximity() +
                fs.getHospitalProximity() +
                fs.getTransportAccess() +
                fs.getSafetyScore()) / 4.0;

        String category;
        if (avg < 4) category = "POOR";
        else if (avg < 6) category = "AVERAGE";
        else if (avg < 8) category = "GOOD";
        else category = "EXCELLENT";

        RatingResult ratingResult = new RatingResult();
        ratingResult.setProperty(property);
        ratingResult.setFinalRating(avg);
        ratingResult.setRatingCategory(category);

        // Validate entity before saving
        Set<ConstraintViolation<RatingResult>> violations = validator.validate(ratingResult);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        ratingResult = ratingResultRepository.save(ratingResult);

        // Create a log entry
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage("Rating generated: " + category);

        Set<ConstraintViolation<RatingLog>> logViolations = validator.validate(log);
        if (!logViolations.isEmpty()) {
            throw new ConstraintViolationException(logViolations);
        }

        ratingLogRepository.save(log);

        return ratingResult;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("RatingResult not found for property id: " + propertyId));
    }
}
