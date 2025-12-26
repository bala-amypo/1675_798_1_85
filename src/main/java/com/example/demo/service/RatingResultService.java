package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;

import java.util.Optional;

public interface RatingResultService {
    RatingResult createRatingResult(Property property, RatingResult result);
    Optional<RatingResult> findByProperty(Property property);
}
