package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingEngineServiceImpl implements RatingEngineService {

    @Autowired
    private RatingResultService ratingResultService;

    @Override
    public RatingResult generateRating(Property property, FacilityScore score) {
        double avgScore = (score.getSchoolProximity() + score.getHospitalProximity() + 
                          score.getTransportAccess() + score.getSafetyScore()) / 4.0;

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avgScore);

        if (avgScore >= 8) {
            result.setRatingCategory("EXCELLENT");
        } else if (avgScore >= 6) {
            result.setRatingCategory("GOOD");
        } else if (avgScore >= 4) {
            result.setRatingCategory("AVERAGE");
        } else {
            result.setRatingCategory("POOR");
        }

        return ratingResultService.createRatingResult(property, result);
    }
}
