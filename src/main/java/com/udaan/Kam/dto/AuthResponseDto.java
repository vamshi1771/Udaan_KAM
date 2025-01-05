package com.udaan.Kam.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String message;
    private String userName;
    private String role;
    private Long userId;
    private String email;

    public AuthResponseDto(String token, String message, String userName, String role, Long userId, String email) {
        this.token = token;
        this.message = message;
        this.userName = userName;
        this.role = role;
        this.userId = userId;
        this.email = email;
    }
}
