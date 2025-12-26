package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }
    
    public List<Property> getPropertiesByCity(String city) {
        return propertyRepository.findByCity(city);
    }
    
    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return propertyRepository.existsById(id);
    }
    
    public long getPropertyCount() {
        return propertyRepository.count();
    }
}