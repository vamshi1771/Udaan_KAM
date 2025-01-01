package com.udaan.Kam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
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

    private POC(Builder builder) {
        this.pocId = builder.pocId;
        this.pocName = builder.pocName;
        this.role = builder.role;
        this.contactNumber = builder.contactNumber;
        this.emailAddress = builder.emailAddress;
        this.restaurantLead = builder.restaurantLead;
    }

    // Getters and setters (if needed)

    public static class Builder {
        private Long pocId;
        private String pocName;
        private String role;
        private String contactNumber;
        private String emailAddress;
        private RestaurantLead restaurantLead;

        public Builder pocId(Long pocId) {
            this.pocId = pocId;
            return this;
        }

        public Builder pocName(String pocName) {
            this.pocName = pocName;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder contactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder restaurantLead(RestaurantLead restaurantLead) {
            this.restaurantLead = restaurantLead;
            return this;
        }

        public POC build() {
            return new POC(this);
        }
    }
}
