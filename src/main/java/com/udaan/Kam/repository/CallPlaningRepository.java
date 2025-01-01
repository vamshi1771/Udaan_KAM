package com.udaan.Kam.repository;

import com.udaan.Kam.dto.RestaurantLastCallDate;
import com.udaan.Kam.entity.CallPlaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallPlaningRepository extends JpaRepository<CallPlaning,Long> {

    @Query(value = "SELECT restaurant_id,last_call_date " +
            "FROM call_planing " +
            "WHERE last_call_date + call_frequency * INTERVAL '1 day' = CURRENT_DATE;", nativeQuery = true)
    List<Object[]> getCalls();
}
