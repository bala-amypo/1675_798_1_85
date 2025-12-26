package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;
import com.example.demo.repository.RatingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingLogService {
    
    @Autowired
    private RatingLogRepository ratingLogRepository;
    
    public RatingLog saveRatingLog(RatingLog ratingLog) {
        return ratingLogRepository.save(ratingLog);
    }
    
    public List<RatingLog> findByProperty(Property property) {
        return ratingLogRepository.findByProperty(property);
    }
    
    public Optional<RatingLog> findById(Long id) {
        return ratingLogRepository.findById(id);
    }
    
    public List<RatingLog> getAllRatingLogs() {
        return ratingLogRepository.findAll();
    }
    
    public void deleteRatingLog(Long id) {
        ratingLogRepository.deleteById(id);
    }
}