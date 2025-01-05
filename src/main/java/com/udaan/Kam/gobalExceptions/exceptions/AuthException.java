package com.udaan.Kam.gobalExceptions.exceptions;

import io.jsonwebtoken.security.SignatureException;

public class AuthException extends SignatureException  {
    private static final long serialVersionUID = 1L;
    public AuthException(String message) {
        super(message);
    }
}