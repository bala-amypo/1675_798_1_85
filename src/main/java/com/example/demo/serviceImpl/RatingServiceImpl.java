package com.example.demo.serviceImpl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingLogService;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository scoreRepository;
    private final RatingResultRepository resultRepository;
    private final RatingLogService logService;

    public RatingServiceImpl(PropertyRepository propertyRepository,
                             FacilityScoreRepository scoreRepository,
                             RatingResultRepository resultRepository,
                             RatingLogService logService) {
        this.propertyRepository = propertyRepository;
        this.scoreRepository = scoreRepository;
        this.resultRepository = resultRepository;
        this.logService = logService;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {

        // 🔁 Thread simulation
        new Thread(() -> logService.addLog(propertyId, "Rating calculation started")).start();

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        FacilityScore score = scoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Facility score not found"));

        double avg = (score.getSchoolProximity()
                + score.getHospitalProximity()
                + score.getTransportAccess()
                + score.getSafetyScore()) / 4.0;

        String category;
        if (avg < 3) category = "POOR";
        else if (avg < 6) category = "AVERAGE";
        else if (avg < 8) category = "GOOD";
        else category = "EXCELLENT";

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avg);
        result.setRatingCategory(category);

        logService.addLog(propertyId, "Rating calculated: " + category);

        return resultRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return resultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }
}
