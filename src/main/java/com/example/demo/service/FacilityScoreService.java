package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityScoreService {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    public FacilityScore saveFacilityScore(FacilityScore facilityScore) {
        return facilityScoreRepository.save(facilityScore);
    }
    
    public Optional<FacilityScore> findByProperty(Property property) {
        return facilityScoreRepository.findByProperty(property);
    }
    
    public Optional<FacilityScore> findById(Long id) {
        return facilityScoreRepository.findById(id);
    }
    
    public void deleteFacilityScore(Long id) {
        facilityScoreRepository.deleteById(id);
    }
}