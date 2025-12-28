package com.example.demo.service.impl;

import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingLogServiceImpl implements RatingLogService {
    
    @Autowired
    private RatingLogRepository ratingLogRepository;
    
    @Override
    public RatingLog createLog(RatingLog log) {
        return ratingLogRepository.save(log);
    }
}