package com.udaan.Kam.dto;

import com.udaan.Kam.entity.RestaurantLead;
import lombok.Data;

import javax.persistence.*;

@Data
public class PocDto {
    private Long pocId;
    private String pocName;
    private String role;
    private String contactNumber;
    private String emailAddress;
    private Long restaurantId;
    private PocDto(Builder builder) {
        this.pocId = builder.pocId;
        this.pocName = builder.pocName;
        this.role = builder.role;
        this.contactNumber = builder.contactNumber;
        this.emailAddress = builder.emailAddress;
        this.restaurantId = builder.restaurantId;
    }

    public static class Builder {
        private Long pocId;
        private String pocName;
        private String role;
        private String contactNumber;
        private String emailAddress;
        private Long restaurantId;

        public Builder setPocId(Long pocId) {
            this.pocId = pocId;
            return this;
        }

        public Builder setPocName(String pocName) {
            this.pocName = pocName;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setRestaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public PocDto build() {
            return new PocDto(this);
        }
    }
}
