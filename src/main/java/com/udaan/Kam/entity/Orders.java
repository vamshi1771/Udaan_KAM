package com.udaan.Kam.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders") // orders made by Restaurants and will be used to calculate performance metrics
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_amount" )
    private Long orderAmount;

    @Column(name = "order_status") // order SuccessFully Completed or Cancelled
    private String orderStatus;
        // Private constructor for Builder
        private Orders(Builder builder) {
            this.orderId = builder.orderId;
            this.restaurantId = builder.restaurantId;
            this.orderDate = builder.orderDate;
            this.orderAmount = builder.orderAmount;
            this.orderStatus = builder.orderStatus;
        }

        // Static Builder Class
        public static class Builder {
            private Long orderId;
            private Long restaurantId;
            private LocalDate orderDate;
            private Long orderAmount;
            private String orderStatus;

            public Builder setOrderId(Long orderId) {
                this.orderId = orderId;
                return this;
            }

            public Builder setRestaurantId(Long restaurantId) {
                this.restaurantId = restaurantId;
                return this;
            }

            public Builder setOrderDate(LocalDate orderDate) {
                this.orderDate = orderDate;
                return this;
            }

            public Builder setOrderAmount(Long orderAmount) {
                this.orderAmount = orderAmount;
                return this;
            }

            public Builder setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
                return this;
            }

            public Orders build() {
                return new Orders(this);
            }
        }
    }

