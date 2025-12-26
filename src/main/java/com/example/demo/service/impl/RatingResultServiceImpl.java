package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingResultServiceImpl implements RatingResultService {

    @Autowired
    private RatingResultRepository ratingResultRepository;

    @Override
    public RatingResult createRatingResult(Property property, RatingResult result) {
        result.setProperty(property);
        return ratingResultRepository.save(result);
    }

    @Override
    public Optional<RatingResult> findByProperty(Property property) {
        return ratingResultRepository.findByProperty(property);
    }
}
