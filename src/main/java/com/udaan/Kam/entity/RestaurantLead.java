package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "restaurant_lead")
public class RestaurantLead {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "restaurant_id")
    private Long restaurant_id;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Embedded
    private Location location;

    @Column(name = "restaurant_type")
    private String restaurantType;

    @Column(name = "lead_status")
    private String leadStatus;

}
