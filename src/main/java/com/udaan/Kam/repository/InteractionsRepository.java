package com.udaan.Kam.repository;

import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.Interactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InteractionsRepository extends JpaRepository<Interactions,Long> {


}
