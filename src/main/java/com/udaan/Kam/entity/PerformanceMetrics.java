package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class PerformanceMetrics {
    @Id
   @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "month")
    private LocalDate date;

    @Column(name = "totalOrders") // total orders placed in a month by a restaurant
    private Long totalOrders;


    @Column(name = "average_order_value") // average amount spend on orders
    private Long averageOrderValue;

}
