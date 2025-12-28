// package com.example.demo.service;

// import com.example.demo.entity.FacilityScore;
// import com.example.demo.entity.Property;

// public interface FacilityScoreService {
//     FacilityScore createScore(Long propertyId, FacilityScore score);
//     FacilityScore getScoreByProperty(Property property);
// }
package com.example.demo.service;

import com.example.demo.entity.FacilityScore;

public interface FacilityScoreService {
    FacilityScore createScore(Long propertyId, FacilityScore score);
    FacilityScore getScore(Long propertyId);
}
