package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    private final PropertyRepository propRepo;
    private final RatingLogRepository logRepo;

    public RatingLogServiceImpl(PropertyRepository propRepo,
                                RatingLogRepository logRepo) {
        this.propRepo = propRepo;
        this.logRepo = logRepo;
    }

    @Override
    public RatingLog addLog(Long propertyId, String message) {
        Property p = propRepo.findById(propertyId).orElseThrow();
        RatingLog log = new RatingLog();
        log.setProperty(p);
        log.setMessage(message);
        return logRepo.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        Property p = propRepo.findById(propertyId).orElseThrow();
        return logRepo.findByProperty(p);
    }
}