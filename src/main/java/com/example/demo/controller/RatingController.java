package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingLogService {

    private final RatingLogRepository logRepository;
    private final PropertyRepository propertyRepository;

    public RatingLogService(RatingLogRepository logRepository,
                            PropertyRepository propertyRepository) {
        this.logRepository = logRepository;
        this.propertyRepository = propertyRepository;
    }

    /**
     * Add a new log entry for a property
     */
    public RatingLog addLog(Long propertyId, String message) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);
        log.setLoggedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    /**
     * Retrieve all logs for a property
     */
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        return logRepository.findByProperty(property);
    }
}
