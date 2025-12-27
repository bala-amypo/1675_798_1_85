package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    private final RatingLogService service;

    public RatingLogController(RatingLogService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<RatingLog> add(@PathVariable Long propertyId,
                                         @RequestBody String message) {
        return ResponseEntity.status(201).body(service.addLog(propertyId, message));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<RatingLog>> list(@PathVariable Long propertyId) {
        return ResponseEntity.ok(service.getLogsByProperty(propertyId));
    }
}