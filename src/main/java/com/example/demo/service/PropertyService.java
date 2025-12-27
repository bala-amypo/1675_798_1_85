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
        if (property == null) {
            throw new BadRequestException("Property cannot be null");
        }

        // Validate property fields
        Set<ConstraintViolation<Property>> violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
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
            throw new BadRequestException("Property id cannot be null");
        }
        return propertyRepository.findById(id);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        if (id == null || property == null) {
            throw new BadRequestException("Property id or property cannot be null");
        }

        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        // Validate updated fields
        Set<ConstraintViolation<Property>> violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        property.setId(id);
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        if (id == null) {
            throw new BadRequestException("Property id cannot be null");
        }

        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        propertyRepository.delete(existing);
    }
}
