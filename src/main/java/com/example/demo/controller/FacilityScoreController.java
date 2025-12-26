package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.service.FacilityScoreService;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    @Autowired
    private FacilityScoreService facilityScoreService;

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> createFacilityScore(@PathVariable Long propertyId, @RequestBody FacilityScore score) {
        Property property = propertyService.findById(propertyId);
        FacilityScore saved = facilityScoreService.createFacilityScore(property, score);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ANALYST')")
    public ResponseEntity<FacilityScore> getFacilityScore(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        Optional<FacilityScore> score = facilityScoreService.findByProperty(property);
        return score.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
