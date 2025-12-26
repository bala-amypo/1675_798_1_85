package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    @Autowired
    private FacilityScoreRepository facilityScoreRepository;

    @Override
    public FacilityScore createFacilityScore(Property property, FacilityScore score) {
        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    @Override
    public Optional<FacilityScore> findByProperty(Property property) {
        return facilityScoreRepository.findByProperty(property);
    }
}
