package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    private final PropertyRepository propertyRepository;
    private final RatingLogRepository logRepository;
    private final Validator validator;

    public RatingLogServiceImpl(PropertyRepository propertyRepository,
                                RatingLogRepository logRepository,
                                Validator validator) {
        this.propertyRepository = propertyRepository;
        this.logRepository = logRepository;
        this.validator = validator;
    }

    @Override
    public RatingLog addLog(Long propertyId, String message) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }
        if (message == null || message.trim().isEmpty()) {
            throw new BadRequestException("Log message cannot be empty");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);

        // Validate the entity before saving
        Set<ConstraintViolation<RatingLog>> violations = validator.validate(log);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return logRepository.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        if (propertyId == null) {
            throw new BadRequestException("Property ID cannot be null");
        }

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        return logRepository.findByProperty(property);
    }
}
