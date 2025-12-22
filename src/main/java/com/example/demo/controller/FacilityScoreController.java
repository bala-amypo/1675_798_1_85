package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService service;

    public FacilityScoreController(FacilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public FacilityScore addScore(
            @PathVariable Long propertyId,
            @RequestBody FacilityScore score) {
        return service.addScore(propertyId, score);
    }

    @GetMapping("/{propertyId}")
    public FacilityScore getScore(@PathVariable Long propertyId) {
        return service.getScoreByProperty(propertyId);
    }
}
