package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final Validator validator;

    public FacilityScoreService(FacilityScoreRepository facilityScoreRepository,
                                PropertyRepository propertyRepository,
                                Validator validator) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.validator = validator;
    }

    public FacilityScore addScore(Long propertyId, FacilityScore fs) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        // Check if a score already exists for this property
        Optional<FacilityScore> existing = facilityScoreRepository.findByProperty(property);
        if (existing.isPresent()) {
            throw new ConstraintViolationException("Facility score already exists for this property", null);
        }

        // Validate score fields (0-10)
        var violations = validator.validate(fs);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        fs.setProperty(property);
        return facilityScoreRepository.save(fs);
    }

    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        return facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("FacilityScore not found for property id: " + propertyId));
    }
}
