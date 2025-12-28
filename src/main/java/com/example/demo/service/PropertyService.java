// package com.example.demo.service;

// import com.example.demo.entity.Property;
// import java.util.List;
// import java.util.Optional;

// public interface PropertyService {
//     Property addProperty(Property property);
//     List<Property> getAllProperties();
//     Optional<Property> getPropertyById(Long id);
//     Property updateProperty(Long id, Property property);
//     void deleteProperty(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {

    // REQUIRED BY TEST
    Property addProperty(Property property);

    // used by controller
    Page<Property> listProperties(Pageable pageable, String city);

    Property getProperty(Long id);
}
