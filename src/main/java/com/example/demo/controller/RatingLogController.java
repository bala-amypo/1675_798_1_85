package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Rating Logs")
public class RatingLogController {

    private final RatingLogService ratingLogService;

    public RatingLogController(RatingLogService ratingLogService) {
        this.ratingLogService = ratingLogService;
    }

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @Operation(summary = "Add rating log for property")
    public ResponseEntity<RatingLog> addLog(@PathVariable Long propertyId,
                                            @RequestBody String message) {
        RatingLog log = ratingLogService.addLog(propertyId, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(log);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @Operation(summary = "Get logs for property")
    public ResponseEntity<List<RatingLog>> getLogs(@PathVariable Long propertyId) {
        List<RatingLog> logs = ratingLogService.getLogsByProperty(propertyId);
        return ResponseEntity.ok(logs);
    }
}
