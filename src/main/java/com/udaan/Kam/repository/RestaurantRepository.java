package com.udaan.Kam.repository;

import com.udaan.Kam.entity.RestaurantLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantLead, Long> {

}
