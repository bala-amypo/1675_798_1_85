package com.example.demo.repository;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {

    /**
     * Fetch all rating logs associated with a given property.
     *
     * @param property the property entity
     * @return list of RatingLog
     */
    List<RatingLog> findByProperty(Property property);
}
