package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PropertyService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    public Property findById(Long id) {
        return propertyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Property not found: " + id));
    }
}
