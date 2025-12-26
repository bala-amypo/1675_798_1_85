package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;
import com.example.demo.entity.FacilityScore;

public interface RatingEngineService {
    RatingResult generateRating(Property property, FacilityScore score);
}
