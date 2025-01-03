package com.udaan.Kam.services;

import com.udaan.Kam.dto.AverageOrdersPerMonth;

import java.util.List;

public interface OrderService {
    List<AverageOrdersPerMonth> getAverageOrders();
}
