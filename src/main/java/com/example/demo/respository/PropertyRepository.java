package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    // Standard Spring Data method
    List<Property> findByCity(String city);
    
    // Custom @Query for HQL method
    @Query("SELECT p FROM Property p WHERE p.city = :city")
    List<Property> findByCityHql(String city);
}
