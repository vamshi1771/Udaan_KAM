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
@Table(name = "interactions")
public class Interactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "interaction_id")
    private Long interactionId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "pocId")
    private Long pocId;

    @Column(name = "interaction_type") // what type of interaction (ex : for order booking, for performance related
    private String interactionType;

    @Column(name = "date")
    private LocalDate interactedDate;

    @Column(name = "interaction_details") // details discussed in Interaction
    private String  interactionDetails;


    private Interactions(Builder builder) {
        this.interactionId = builder.interactionId;
        this.restaurantId = builder.restaurantId;
        this.pocId = builder.pocId;
        this.interactionType = builder.interactionType;
        this.interactedDate = builder.interactedDate;
        this.interactionDetails = builder.interactionDetails;
    }

    public static class Builder {
        private Long interactionId;
        private Long restaurantId;
        private Long pocId;
        private String interactionType;
        private LocalDate interactedDate;
        private String interactionDetails;

        public Builder setInteractionId(Long interactionId) {
            this.interactionId = interactionId;
            return this;
        }

        public Builder setRestaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder setPocId(Long pocId) {
            this.pocId = pocId;
            return this;
        }

        public Builder setInteractionType(String interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public Builder setInteractedDate(LocalDate interactedDate) {
            this.interactedDate = interactedDate;
            return this;
        }

        public Builder setInteractionDetails(String interactionDetails) {
            this.interactionDetails = interactionDetails;
            return this;
        }

        public Interactions build() {
            return new Interactions(this);
        }
    }

}
