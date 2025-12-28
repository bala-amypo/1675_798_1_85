package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    @Override
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }
    
    @Override
    public Property updateProperty(Long id, Property property) {
        property.setId(id);
        return propertyRepository.save(property);
    }
    
    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}