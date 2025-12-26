package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyService propertyService;

    public FacilityScoreController(FacilityScoreRepository facilityScoreRepository,
                                   PropertyService propertyService) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyService = propertyService;
    }

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> create(@PathVariable Long propertyId,
                                               @Valid @RequestBody FacilityScore score) {
        Property property = propertyService.findById(propertyId);

        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new BadRequestException("Facility score already exists for property " + propertyId);
        }

        score.setProperty(property);
        FacilityScore saved = facilityScoreRepository.save(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityScore> get(@PathVariable Long propertyId) {
        Property property = propertyService.findById(propertyId);
        return facilityScoreRepository.findByProperty(property)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
