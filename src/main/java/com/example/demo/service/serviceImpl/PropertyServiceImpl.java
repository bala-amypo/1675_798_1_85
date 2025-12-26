// package com.example.demo.service.impl;

// import com.example.demo.entity.Property;
// import com.example.demo.repository.PropertyRepository;
// import com.example.demo.service.PropertyService;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;

// @Service
// @Transactional
// public class PropertyServiceImpl implements PropertyService {

//     private final PropertyRepository propertyRepository;

//     public PropertyServiceImpl(PropertyRepository propertyRepository) { // constructor DI
//         this.propertyRepository = propertyRepository;
//     }

//     @Override
//     public Property addProperty(Property property) {
//         return propertyRepository.save(property);
//     }

//     @Override
//     public List<Property> getAllProperties() {
//         return propertyRepository.findAll();
//     }
// }
