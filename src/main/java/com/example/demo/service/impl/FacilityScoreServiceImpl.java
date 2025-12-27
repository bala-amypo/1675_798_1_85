package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository fsRepo;
    private final PropertyRepository propRepo;
    private final Validator validator;

    public FacilityScoreServiceImpl(FacilityScoreRepository fsRepo,
                                    PropertyRepository propRepo,
                                    Validator validator) {
        this.fsRepo = fsRepo;
        this.propRepo = propRepo;
        this.validator = validator;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Property not found with ID: " + propertyId));

        // Check if score already exists
        if (fsRepo.findByProperty(property).isPresent()) {
            throw new BadRequestException("Facility score already exists for this property");
        }

        // Validate score fields
        Set<ConstraintViolation<FacilityScore>> violations = validator.validate(score);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Invalid FacilityScore values", violations);
        }

        score.setProperty(property);
        return fsRepo.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Property not found with ID: " + propertyId));

        return fsRepo.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Facility score not found for property ID: " + propertyId));
    }
}
