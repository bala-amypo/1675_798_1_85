package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final PropertyRepository propRepo;
    private final FacilityScoreRepository fsRepo;
    private final RatingResultRepository rrRepo;
    private final RatingLogRepository logRepo;

    public RatingServiceImpl(PropertyRepository propRepo,
                             FacilityScoreRepository fsRepo,
                             RatingResultRepository rrRepo,
                             RatingLogRepository logRepo) {
        this.propRepo = propRepo;
        this.fsRepo = fsRepo;
        this.rrRepo = rrRepo;
        this.logRepo = logRepo;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {

        Property p = propRepo.findById(propertyId).orElseThrow();
        FacilityScore fs = fsRepo.findByProperty(p).orElseThrow();

        double avg = (fs.getSchoolProximity()
                + fs.getHospitalProximity()
                + fs.getTransportAccess()
                + fs.getSafetyScore()) / 4.0;

        String category;
        if (avg < 4) category = "POOR";
        else if (avg < 6) category = "AVERAGE";
        else if (avg < 8) category = "GOOD";
        else category = "EXCELLENT";

        RatingResult rr = new RatingResult();
        rr.setProperty(p);
        rr.setFinalRating(avg);
        rr.setRatingCategory(category);

        rr = rrRepo.save(rr);

        RatingLog log = new RatingLog();
        log.setProperty(p);
        log.setMessage("Rating generated: " + category);
        logRepo.save(log);

        return rr;
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        Property p = propRepo.findById(propertyId).orElseThrow();
        return rrRepo.findByProperty(p).orElseThrow();
    }
}