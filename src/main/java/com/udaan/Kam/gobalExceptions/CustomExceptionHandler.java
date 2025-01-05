package com.udaan.Kam.gobalExceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udaan.Kam.dto.ErrorResponse;
import com.udaan.Kam.gobalExceptions.exceptions.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

        @ExceptionHandler(AuthException.class)
        public void handleInvalidTokenException(final AuthException ex, final HttpServletRequest request) {
            ErrorResponse error = new ErrorResponse("Invalid Token", "JWT signature does not match. Please, login again");
            sendErrorResponse(request, error, HttpStatus.UNAUTHORIZED);
        }

    private void sendErrorResponse (HttpServletRequest request, ErrorResponse errorResponse, HttpStatus httpStatus) {
        try {
            String json = new ObjectMapper().writeValueAsString(errorResponse);
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            response.setStatus(httpStatus.value());
            response.getWriter().write(json);
        } catch (Exception e) {
            throw new RuntimeException("IOError writing to response stream", e);
        }
    }

}

