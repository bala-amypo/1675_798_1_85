package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;
import com.example.demo.service.RatingLogService;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    @Autowired
    private RatingLogService ratingLogService;

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingLog> createRatingLog(@PathVariable Long propertyId, @RequestBody RatingLog log) {
        Property property = propertyService.findById(propertyId);
        RatingLog saved = ratingLogService.createRatingLog(property, log);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ANALYST')")
    public ResponseEntity<List<RatingLog>> getRatingLogs(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        List<RatingLog> logs = ratingLogService.findByProperty(property);
        return ResponseEntity.ok(logs);
    }
}
