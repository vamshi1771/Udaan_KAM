package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "location")
    private String location;

    @Column(name = "restaurant_type")
    private String restaurantType;

    @Column(name = "lead_status")
    private String leadStatus;

    @OneToMany(mappedBy = "restaurantLead", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<POC> pocList = new ArrayList<>();

    private RestaurantLead(Builder builder) {
        this.restaurantId = builder.restaurantId;
        this.restaurantName = builder.restaurantName;
        this.location = builder.location;
        this.restaurantType = builder.restaurantType;
        this.leadStatus = builder.leadStatus;
    }

    public static class Builder {
        private Long restaurantId;
        private String restaurantName;
        private String location;
        private String restaurantType;
        private String leadStatus;


        public Builder setRestaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder setRestaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setRestaurantType(String restaurantType) {
            this.restaurantType = restaurantType;
            return this;
        }

        public Builder setLeadStatus(String leadStatus) {
            this.leadStatus = leadStatus;
            return this;
        }

        public RestaurantLead build() {
            return new RestaurantLead(this);
        }

    }
}
