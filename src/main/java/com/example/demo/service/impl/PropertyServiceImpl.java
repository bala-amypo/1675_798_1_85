package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final Validator validator;

    public PropertyServiceImpl(PropertyRepository propertyRepository, Validator validator) {
        this.propertyRepository = propertyRepository;
        this.validator = validator;
    }

    @Override
    public Property addProperty(Property property) {
        // Validate the property object
        Set<ConstraintViolation<Property>> violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Invalid Property data", violations);
        }

        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> getPropertyById(Long id) {
        if (id == null) {
            throw new BadRequestException("Property ID cannot be null");
        }
        return propertyRepository.findById(id);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));

        // Validate updated data
        Set<ConstraintViolation<Property>> violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Invalid Property data", violations);
        }

        // Copy properties
        existing.setTitle(property.getTitle());
        existing.setAddress(property.getAddress());
        existing.setCity(property.getCity());
        existing.setPrice(property.getPrice());
        existing.setAreaSqFt(property.getAreaSqFt());
        existing.setName(property.getName());
        existing.setLocation(property.getLocation());

        return propertyRepository.save(existing);
    }

    @Override
    public void deleteProperty(Long id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        propertyRepository.delete(existing);
    }
}
