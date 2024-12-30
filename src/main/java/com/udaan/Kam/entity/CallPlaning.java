package com.udaan.Kam.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "call_frequency")
    private Long call_frequency;

    @Column(name = "last_call_date")
    private LocalDate lastCallDate;
}
