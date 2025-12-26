package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.RatingService;
import com.example.demo.service.RatingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private RatingService ratingService;
    
    @Autowired
    private RatingResultService ratingResultService;
    
    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        RatingResult result = ratingService.generateRating(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYST')")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        RatingResult result = ratingResultService.findByProperty(property).orElseThrow();
        return ResponseEntity.ok(result);
    }
}