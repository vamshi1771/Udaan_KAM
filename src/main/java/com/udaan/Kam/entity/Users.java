package com.udaan.Kam.entity;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "users") // Authentication purpose
@Check(constraints = "role IN ('ADMIN', 'CUSTOMER')")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email")
    private String email;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_account_blocked")
    private Boolean isAccountBlocked = false;
}