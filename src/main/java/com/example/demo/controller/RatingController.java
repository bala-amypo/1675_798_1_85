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
        RatingResult result = service.generateRating(propertyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> get(@PathVariable Long propertyId) {
        RatingResult result = service.getRating(propertyId);
        return ResponseEntity.ok(result);
    }
}
