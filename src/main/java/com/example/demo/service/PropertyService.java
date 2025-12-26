package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Transactional(readOnly = true)
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Property findById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found: " + id));
    }
}
