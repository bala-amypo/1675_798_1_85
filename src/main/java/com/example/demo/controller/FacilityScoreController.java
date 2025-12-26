package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
@Tag(name = "Facility Scores")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Submit facility score for property")
    public ResponseEntity<FacilityScore> addScore(@PathVariable Long propertyId,
                                                  @Valid @RequestBody FacilityScore score) {
        FacilityScore saved = facilityScoreService.addScore(propertyId, score);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @Operation(summary = "Get facility score for property")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        FacilityScore score = facilityScoreService.getScoreByProperty(propertyId);
        return ResponseEntity.ok(score);
    }
}
