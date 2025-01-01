package com.udaan.Kam.repository;

import com.udaan.Kam.entity.POC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocRepository extends JpaRepository<POC,Long> {
}
