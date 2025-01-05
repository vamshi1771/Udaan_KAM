package com.udaan.Kam.repository;

import com.udaan.Kam.dto.RestaurantLastCallDate;
import com.udaan.Kam.entity.CallPlaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallPlaningRepository extends JpaRepository<CallPlaning,Long> {

    @Query(value = "SELECT c1.restaurant_id, c1.last_call_date, r1.restaurant_name, c1.call_frequency " +
    "FROM call_planing c1 " +
    "JOIN restaurant_lead r1 ON r1.restaurant_id = c1.restaurant_id "+
    "WHERE last_call_date + c1.call_frequency * INTERVAL '1 day' = CURRENT_DATE ", nativeQuery = true)
    List<Object[]> getCalls();
}
