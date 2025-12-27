package com.example.demo.repository;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {

    /**
     * Fetch the rating result associated with a specific property.
     *
     * @param property the property entity
     * @return Optional containing the RatingResult if present
     */
    Optional<RatingResult> findByProperty(Property property);
}
