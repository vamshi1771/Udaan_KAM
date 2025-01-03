package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.AverageOrdersPerMonth;
import com.udaan.Kam.repository.OrderRepository;
import com.udaan.Kam.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<AverageOrdersPerMonth> getAverageOrders() {
        List<Object[]> averageOrders =  orderRepository.findAverageOrdersPerMonth();
        return averageOrders.stream()
                .map(row -> new AverageOrdersPerMonth(
                        castToString(row[0]),      // Check and cast row[0] to String
                        castToLong(row[1]),        // Check and cast row[1] to Long
                        castToLong(row[2]),        // Check and cast row[2] to Long
                        castToLong(row[3]),        // Check and cast row[3] to Long
                        castToString(row[4])       // Check and cast row[4] to String
                ))
                .collect(Collectors.toList());
        }


    private Long castToLong(Object value) {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).longValue();
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).longValue();
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            throw new IllegalArgumentException("Unexpected type for Long conversion: " + value.getClass().getName());
        }
    }
    private String castToString(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException("Unexpected type for String conversion: " + value.getClass().getName());
        }
    }
    }

