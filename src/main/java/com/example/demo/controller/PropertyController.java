package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Property> addProperty(@Valid @RequestBody Property property) {
        Property saved = propertyService.addProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYST')")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyRepository.findAll());
    }
}