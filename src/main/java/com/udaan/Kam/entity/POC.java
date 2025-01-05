package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Builder
@Table(name = "poc")
public class POC {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "poc_id")
    private Long pocId;

    @Column(name = "poc_name")
    private String pocName;

    @Column(name = "role")
    private String role;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantLead restaurantLead;

}
