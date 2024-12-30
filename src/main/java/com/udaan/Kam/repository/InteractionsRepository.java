package com.udaan.Kam.repository;

import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.Interactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InteractionsRepository extends JpaRepository<Interactions,Long> {


}
