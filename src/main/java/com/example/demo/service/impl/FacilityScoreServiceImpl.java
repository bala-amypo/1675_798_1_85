package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository fsRepo;
    private final PropertyRepository propRepo;

    public FacilityScoreServiceImpl(FacilityScoreRepository fsRepo,
                                    PropertyRepository propRepo) {
        this.fsRepo = fsRepo;
        this.propRepo = propRepo;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property p = propRepo.findById(propertyId).orElseThrow();

        if (fsRepo.findByProperty(p).isPresent())
            throw new RuntimeException("Facility score already exists");

        score.setProperty(p);
        return fsRepo.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        Property p = propRepo.findById(propertyId).orElseThrow();
        return fsRepo.findByProperty(p).orElseThrow();
    }
}