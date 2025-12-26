// package com.example.demo.service.impl;

// import com.example.demo.entity.FacilityScore;
// import com.example.demo.entity.Property;
// import com.example.demo.entity.RatingLog;
// import com.example.demo.entity.RatingResult;
// import com.example.demo.repository.FacilityScoreRepository;
// import com.example.demo.repository.PropertyRepository;
// import com.example.demo.repository.RatingLogRepository;
// import com.example.demo.repository.RatingResultRepository;
// import com.example.demo.service.RatingService;
// import jakarta.persistence.EntityNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// @Service
// @Transactional
// public class RatingServiceImpl implements RatingService {

//     private final PropertyRepository propertyRepository;
//     private final FacilityScoreRepository facilityScoreRepository;
//     private final RatingResultRepository ratingResultRepository;
//     private final RatingLogRepository ratingLogRepository;

//     public RatingServiceImpl(PropertyRepository propertyRepository,
//                              FacilityScoreRepository facilityScoreRepository,
//                              RatingResultRepository ratingResultRepository,
//                              RatingLogRepository ratingLogRepository) {
//         this.propertyRepository = propertyRepository;
//         this.facilityScoreRepository = facilityScoreRepository;
//         this.ratingResultRepository = ratingResultRepository;
//         this.ratingLogRepository = ratingLogRepository;
//     }

//     @Override
//     public RatingResult generateRating(Long propertyId) {
//         Property property = propertyRepository.findById(propertyId)
//                 .orElseThrow(() -> new EntityNotFoundException("Property not found"));
//         FacilityScore score = facilityScoreRepository.findByProperty(property)
//                 .orElseThrow(() -> new IllegalStateException("Facility score missing"));

//         double finalRating = (score.getSchoolProximity()
//                 + score.getHospitalProximity()
//                 + score.getTransportAccess()
//                 + score.getSafetyScore()) / 4.0;

//         String category;
//         if (finalRating < 4.0) {
//             category = "POOR";
//         } else if (finalRating < 7.0) {
//             category = "AVERAGE";
//         } else if (finalRating < 9.0) {
//             category = "GOOD";
//         } else {
//             category = "EXCELLENT";
//         }

//         RatingResult ratingResult = new RatingResult();
//         ratingResult.setProperty(property);
//         ratingResult.setFinalRating(finalRating);
//         ratingResult.setRatingCategory(category);
//         RatingResult saved = ratingResultRepository.save(ratingResult);

//         RatingLog log = new RatingLog();
//         log.setProperty(property);
//         log.setMessage("Rating generated: " + finalRating);
//         ratingLogRepository.save(log);

//         return saved;
//     }

//     @Override
//     public RatingResult getRating(Long propertyId) {
//         Property property = propertyRepository.findById(propertyId)
//                 .orElseThrow(() -> new EntityNotFoundException("Property not found"));
//         return ratingResultRepository.findByProperty(property).orElse(null);
//     }
// }
