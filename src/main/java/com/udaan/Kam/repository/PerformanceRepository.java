package com.udaan.Kam.repository;

import com.udaan.Kam.entity.PerformanceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<PerformanceMetrics,Long> {

   @Query(value = "select * from PerformanceMetrics where restaurant_id = ?1"+
           "AND EXTRACT(YEAR FROM month) = EXTRACT(YEAR FROM CAST(?2 AS DATE))"+
           "AND EXTRACT(MONTH FROM month) = EXTRACT(MONTH FROM CAST(?2 AS DATE)) ", nativeQuery = true)
   PerformanceMetrics findByRestaurantIdAndDate(Long RestaurantId, LocalDate date);
}
