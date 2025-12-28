package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class RatingLogController {
    
    @Autowired
    private RatingLogService ratingLogService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RatingLog> createLog(@RequestBody RatingLog log) {
        RatingLog savedLog = ratingLogService.createLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
    }
}