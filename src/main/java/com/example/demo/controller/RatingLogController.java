package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    private final RatingLogService service;

    public RatingLogController(RatingLogService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public RatingLog addLog(
            @PathVariable Long propertyId,
            @RequestParam String message) {
        return service.addLog(propertyId, message);
    }

    @GetMapping("/property/{propertyId}")
    public List<RatingLog> getLogs(@PathVariable Long propertyId) {
        return service.getLogsByProperty(propertyId);
    }
}
