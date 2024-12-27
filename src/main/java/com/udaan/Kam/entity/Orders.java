package com.udaan.Kam.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders") // will be used for calculating performance metrics
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_amount" )
    private Long orderAmount;

    @Column(name = "order_status") // order SuccessFull Completed or Canceled
    private String orderStatus;

}
