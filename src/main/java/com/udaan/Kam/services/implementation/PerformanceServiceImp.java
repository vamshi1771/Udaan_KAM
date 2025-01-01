package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.MonthOrderCount;
import com.udaan.Kam.dto.PerformanceDto;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.repository.OrderRepository;
import com.udaan.Kam.repository.RestaurantRepository;
import com.udaan.Kam.services.PerformanceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PerformanceServiceImp implements PerformanceMetricsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<PerformanceDto> getPerformance(Long year) {
        List<Object[]> rawData = orderRepository.findRestaurantOrdersByYear(year);

        // Map to hold performance data grouped by restaurant ID
        Map<Long, Map<Integer, MonthOrderCount>> restaurantData = new HashMap<>();

        for (Object[] record : rawData) {
            Long restaurantId = ((Number) record[0]).longValue();
            Integer month = ((Number) record[1]).intValue();
            Long totalOrders = ((Number) record[2]).longValue();
            Long averageOrderAmount = ((Number) record[3]).longValue();

            restaurantData
                    .computeIfAbsent(restaurantId, k -> new HashMap<>())
                    .put(month, new MonthOrderCount(month, totalOrders, averageOrderAmount));
        }

        // Build final DTO list
        List<PerformanceDto> performanceDtos = new ArrayList<>();
        for (Map.Entry<Long, Map<Integer, MonthOrderCount>> entry : restaurantData.entrySet()) {
            Long restaurantId = entry.getKey();
            Map<Integer, MonthOrderCount> monthData = entry.getValue();

            List<MonthOrderCount> monthOrderCounts = new ArrayList<>();
            for (int month = 1; month <= 12; month++) {
                monthOrderCounts.add(
                        monthData.getOrDefault(month, new MonthOrderCount(month, 0L, 0L))
                );
            }


            PerformanceDto dto = new PerformanceDto();
            Optional<RestaurantLead> restaurantLead = restaurantRepository.findById(restaurantId);
            if(restaurantLead.isPresent()){
                dto.setRestaurantName(restaurantLead.get().getRestaurantName());
            }
            dto.setRestaurantId(restaurantId);
            dto.setMonthOrderCounts(monthOrderCounts);
            performanceDtos.add(dto);
        }

        return performanceDtos;
    }
}
