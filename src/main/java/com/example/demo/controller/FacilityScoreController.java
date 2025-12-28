// package com.example.demo.controller;

// import com.example.demo.entity.FacilityScore;
// import com.example.demo.entity.Property;
// import com.example.demo.service.FacilityScoreService;
// import com.example.demo.service.PropertyService;
// import jakarta.validation.Valid;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/scores")
// public class FacilityScoreController {
    
//     @Autowired
//     private FacilityScoreService facilityScoreService;
    
//     @Autowired
//     private PropertyService propertyService;
    
//     @PostMapping("/{propertyId}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public ResponseEntity<FacilityScore> createScore(@PathVariable Long propertyId, 
//                                                    @Valid @RequestBody FacilityScore score) {
//         try {
//             FacilityScore savedScore = facilityScoreService.createScore(propertyId, score);
//             return ResponseEntity.status(HttpStatus.CREATED).body(savedScore);
//         } catch (RuntimeException e) {
//             return ResponseEntity.badRequest().build();
//         }
//     }
    
//     @GetMapping("/{propertyId}")
//     @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYST')")
//     public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
//         Property property = propertyService.getPropertyById(propertyId).orElse(null);
//         if (property == null) {
//             return ResponseEntity.notFound().build();
//         }
        
//         FacilityScore score = facilityScoreService.getScoreByProperty(property);
//         return score != null ? ResponseEntity.ok(score) : ResponseEntity.notFound().build();
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService service;

    public FacilityScoreController(FacilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> create(
            @PathVariable Long propertyId,
            @RequestBody FacilityScore score) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createScore(propertyId, score));
    }

    @GetMapping("/{propertyId}")
    public FacilityScore get(@PathVariable Long propertyId) {
        return service.getScore(propertyId);
    }
}
