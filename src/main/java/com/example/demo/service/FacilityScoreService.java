package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final Validator validator;

    public FacilityScoreServiceImpl(FacilityScoreRepository facilityScoreRepository,
                                    PropertyRepository propertyRepository,
                                    Validator validator) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.validator = validator;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        if (score == null) {
            throw new BadRequestException("FacilityScore cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        // Prevent duplicate score
        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new BadRequestException("Facility score already exists for this property");
        }

        // Validate fields using javax.validation
        Set<ConstraintViolation<FacilityScore>> violations = validator.validate(score);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        return facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("FacilityScore not found for property id: " + propertyId));
    }
}
