package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;
import com.example.demo.service.RatingResultService;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingResultController {

    @Autowired
    private RatingResultService ratingResultService;

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        RatingResult result = ratingResultService.createRatingResult(property, new RatingResult());
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ANALYST')")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        Optional<RatingResult> result = ratingResultService.findByProperty(property);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
