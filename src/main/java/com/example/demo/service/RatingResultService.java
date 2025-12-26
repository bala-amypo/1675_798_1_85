package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingResultService {
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    public RatingResult saveRatingResult(RatingResult ratingResult) {
        return ratingResultRepository.save(ratingResult);
    }
    
    public Optional<RatingResult> findByProperty(Property property) {
        return ratingResultRepository.findByProperty(property);
    }
    
    public Optional<RatingResult> findById(Long id) {
        return ratingResultRepository.findById(id);
    }
    
    public void deleteRatingResult(Long id) {
        ratingResultRepository.deleteById(id);
    }
}