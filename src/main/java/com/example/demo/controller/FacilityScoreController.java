package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {
    
    @Autowired
    private FacilityScoreService facilityScoreService;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> createScore(@PathVariable Long propertyId, 
                                                   @Valid @RequestBody FacilityScore score) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        
        if (facilityScoreService.findByProperty(property).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        score.setProperty(property);
        FacilityScore saved = facilityScoreService.saveFacilityScore(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYST')")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        FacilityScore score = facilityScoreService.findByProperty(property).orElseThrow();
        return ResponseEntity.ok(score);
    }
}