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
        RatingLog log = service.addLog(propertyId, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(log);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<RatingLog>> get(@PathVariable Long propertyId) {
        List<RatingLog> logs = service.getLogsByProperty(propertyId);
        return ResponseEntity.ok(logs);
    }
}
