package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    // Only ADMIN can add properties
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Property> add(@Valid @RequestBody Property property) {
        Property saved = service.addProperty(property);
        if (saved == null) {
            throw new ResourceNotFoundException("Failed to add property");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // All authenticated users can list properties
    @GetMapping
    public ResponseEntity<List<Property>> list() {
        List<Property> properties = service.getAllProperties();
        return ResponseEntity.ok(properties);
    }
}
