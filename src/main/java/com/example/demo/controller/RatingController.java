package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @PostMapping("/generate/{propertyId}")
    public ResponseEntity<RatingResult> generate(@PathVariable Long propertyId) {
        return ResponseEntity.status(201).body(service.generateRating(propertyId));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> get(@PathVariable Long propertyId) {
        return ResponseEntity.ok(service.getRating(propertyId));
    }
}