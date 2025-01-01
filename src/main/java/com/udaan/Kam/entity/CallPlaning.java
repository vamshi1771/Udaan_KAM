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

@Entity
@Table(name = "call_planing")
public class CallPlaning {
    @Id
    private Long restaurantId; // This acts as both primary key and foreign key

    @OneToOne
    @MapsId
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantLead restaurantLead;

    @Column(name = "call_frequency")
    private Long callFrequency;

    @Column(name = "last_call_date")
    private LocalDate lastCallDate;
}
