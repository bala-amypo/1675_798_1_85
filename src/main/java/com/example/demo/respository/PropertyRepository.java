package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Derived method used in tests
    List<Property> findByCity(String city);

    // HQL method required by tests
    @Query("select p from Property p where p.city = :city")
    List<Property> findByCityHql(String city);
}
