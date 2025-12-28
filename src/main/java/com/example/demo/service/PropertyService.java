package com.example.demo.service;

import com.example.demo.entity.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyService {
    Property addProperty(Property property);
    List<Property> getAllProperties();
    Optional<Property> getPropertyById(Long id);
    Property updateProperty(Long id, Property property);
    void deleteProperty(Long id);
}