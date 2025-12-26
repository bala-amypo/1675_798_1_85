package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;

import java.util.List;

public interface RatingLogService {
    RatingLog createRatingLog(Property property, RatingLog log);
    List<RatingLog> findByProperty(Property property);
}
