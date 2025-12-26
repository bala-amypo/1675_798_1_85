package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Generate rating for property")
    public ResponseEntity<RatingResult> generate(@PathVariable Long propertyId) {
        RatingResult result = ratingService.generateRating(propertyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @Operation(summary = "Get rating for property")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        RatingResult result = ratingService.getRating(propertyId);
        return ResponseEntity.ok(result);
    }
}
