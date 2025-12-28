package com.example.demo.controller;

import com.example.demo.entity.Property;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Property> add(@Valid @RequestBody Property p) {
        return ResponseEntity.status(201).body(service.addProperty(p));
    }

    @GetMapping
    public ResponseEntity<List<Property>> list() {
        return ResponseEntity.ok(service.getAllProperties());
    }
}