package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Derived query
    List<Property> findByCity(String city);

    // HQL query (EXACT name required)
    @Query("select p from Property p where p.city = :city")
    List<Property> findByCityHql(@Param("city") String city);
}
