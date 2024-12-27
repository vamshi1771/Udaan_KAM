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
@Table(name = "interactions")
public class Interactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "interaction_id")
    private Long interaction_id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "pocId")
    private Long pocId;

    @Column(name = "interaction_type")
    private String interactionType;

    @Column(name = "date")
    private Date interactedDate;

    @Column(name = "interaction_details")
    private String  interactionDetails;
}
