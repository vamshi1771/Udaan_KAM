package com.udaan.Kam.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Data
public class InteractionDto {
    private Long restaurantId;
    private Long pocId;
    private String interactionType;
    private LocalDate interactedDate;
    private String  interactionDetails;
    private LocalDate orderDate;
    private Long orderAmount;
    private String orderStatus;
}
