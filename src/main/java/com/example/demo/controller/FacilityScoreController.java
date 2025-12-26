package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private PropertyService propertyService;
    
    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> createScore(
            @PathVariable Long propertyId, 
            @Valid @RequestBody FacilityScore score) {
        
        Property property = propertyService.findById(propertyId);
        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new RuntimeException("Facility score already exists for this property");
        }
        
        score.setProperty(property);
        FacilityScore saved = facilityScoreRepository.save(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        return facilityScoreRepository.findByProperty(property)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
