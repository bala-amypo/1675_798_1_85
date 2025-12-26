package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import com.example.demo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        return ResponseEntity.status(201).body(propertyService.addProperty(property));
    }

    @GetMapping
    @PreAuthorize("hasRole('ANALYST')")
    public ResponseEntity<List<Property>> getProperties() {
        return ResponseEntity.ok(propertyRepository.findAll());
    }
}
