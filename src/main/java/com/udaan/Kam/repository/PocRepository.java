package com.udaan.Kam.repository;

import com.udaan.Kam.entity.POC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PocRepository extends JpaRepository<POC,Long> {
    @Query(value = "SELECT * FROM POC WHERE restaurant_id = ?1", nativeQuery = true)
    List<POC> findAllByRestaurantId(Long restaurantId);
}
