package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.InteractionDto;
import com.udaan.Kam.entity.CallPlaning;
import com.udaan.Kam.entity.Interactions;
import com.udaan.Kam.entity.Orders;
import com.udaan.Kam.entity.PerformanceMetrics;
import com.udaan.Kam.repository.CallPlaningRepository;
import com.udaan.Kam.repository.InteractionsRepository;
import com.udaan.Kam.repository.OrderRepository;
import com.udaan.Kam.repository.PerformanceRepository;
import com.udaan.Kam.services.InteractionService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    private InteractionsRepository interactionsRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CallPlaningRepository callPlaningRepository;

    @Autowired
    PerformanceRepository performanceRepository;
    @Transactional
    @Override
    public void makeInteraction(InteractionDto interaction){
        Interactions interactions = new Interactions.Builder()
                .setInteractedDate(LocalDate.now())
                .setInteractionDetails(interaction.getInteractionDetails())
                .setInteractionType(interaction.getInteractionType())
                .setPocId(interaction.getPocId())
                .setRestaurantId(interaction.getRestaurantId())
                .build();

        interactionsRepository.save(interactions);
        if(interaction.getInteractionType().equals("ORDER")){
            Orders order = new Orders.Builder()
                    .setRestaurantId(interaction.getRestaurantId())
                    .setOrderAmount(interaction.getOrderAmount())
                    .setOrderDate(LocalDate.now())
                    .setOrderStatus(interaction.getOrderStatus())
                    .build();
            orderRepository.save(order);

//            PerformanceMetrics performanceMetrics = performanceRepository.findByRestaurantIdAndDate(interaction.getRestaurantId(), LocalDate.now());
//            if (performanceMetrics != null) {
//                // Update existing record
//                long currentTotalOrders = performanceMetrics.getTotalOrders();
//                long currentAverageOrderValue = performanceMetrics.getAverageOrderValue();
//
//                // Update total orders
//                performanceMetrics.setTotalOrders(currentTotalOrders + 1);
//
//                // Compute updated average order value
//                long updatedAverageOrderValue =
//                        (currentAverageOrderValue * currentTotalOrders + interaction.getOrderAmount())
//                                / (currentTotalOrders + 1);
//
//                performanceMetrics.setAverageOrderValue(updatedAverageOrderValue);
//                performanceRepository.save(performanceMetrics);
//            } else {
//                // Create a new record
//                PerformanceMetrics newMetrics = new PerformanceMetrics();
//                newMetrics.setRestaurantId(interaction.getRestaurantId());
//                newMetrics.setDate(LocalDate.now());
//                newMetrics.setTotalOrders(1L);
//                newMetrics.setAverageOrderValue(interaction.getOrderAmount());
//                performanceRepository.save(newMetrics);
//            }
        }
            CallPlaning callPlaning = callPlaningRepository.getById(interaction.getRestaurantId());
        callPlaning.setLastCallDate(LocalDate.now());
        callPlaningRepository.save(callPlaning);
    }
}
