package com.udaan.Kam.services;

import com.udaan.Kam.dto.PerformanceDto;

import java.util.List;

public interface PerformanceMetricsService {
    List<PerformanceDto> getPerformance(Long year,Long restaurantId);
}
