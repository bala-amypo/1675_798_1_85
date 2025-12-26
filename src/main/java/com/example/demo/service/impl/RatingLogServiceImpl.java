package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;
import com.example.demo.repository.RatingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    @Autowired
    private RatingLogRepository ratingLogRepository;

    @Override
    public RatingLog createRatingLog(Property property, RatingLog log) {
        log.setProperty(property);
        return ratingLogRepository.save(log);
    }

    @Override
    public List<RatingLog> findByProperty(Property property) {
        return ratingLogRepository.findByProperty(property);
    }
}
