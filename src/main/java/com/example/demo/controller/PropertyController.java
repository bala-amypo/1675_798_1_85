package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PropertyRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final Validator validator;

    public PropertyService(PropertyRepository propertyRepository, Validator validator) {
        this.propertyRepository = propertyRepository;
        this.validator = validator;
    }

    /**
     * Add a property after validation.
     * @param property Property to add
     * @return saved property
     */
    public Property addProperty(Property property) {
        // Validate price > 0 and area >= 100
        var violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        // Optional: enforce unique title/address combination
        Optional<Property> existing = propertyRepository.findByCityAndAddress(property.getCity(), property.getAddress());
        if (existing.isPresent()) {
            throw new ConstraintViolationException("Property already exists in this city/address", null);
        }

        return propertyRepository.save(property);
    }

    /**
     * Get all properties
     * @return list of properties
     */
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Fetch a single property by ID
     * @param propertyId ID
     * @return property
     */
    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));
    }
}
