package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "performance_metrics") // will get
public class PerformanceMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long performanceId;

   @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "month")
    private Date month;

    @Column(name = "totalOrders") // total orders placed in a month by a restaurant
    private Long totalOrders;


    @Column(name = "average_order_value") // maxvalue will be 10 and if it is under 5 UnderPerforming and 5 -7 good and 8-10 Excellent
    private Long averageOrderValue;

}
