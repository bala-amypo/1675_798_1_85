package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingResult> generate(@PathVariable Long propertyId) {
        RatingResult rr = ratingService.generateRating(propertyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(rr);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYST')")
    public ResponseEntity<RatingResult> get(@PathVariable Long propertyId) {
        return ratingService.getRatingByPropertyId(propertyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
