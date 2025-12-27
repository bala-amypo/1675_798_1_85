package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService service;

    public FacilityScoreController(FacilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<?> add(@PathVariable Long propertyId,
                                 @Valid @RequestBody FacilityScore fs) {
        return ResponseEntity.status(201).body(service.addScore(propertyId, fs));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<?> get(@PathVariable Long propertyId) {
        return ResponseEntity.ok(service.getScoreByProperty(propertyId));
    }
}