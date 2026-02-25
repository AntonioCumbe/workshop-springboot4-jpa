package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // ✅ import correto
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            e.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class) // Para qualquer outro erro
    public ResponseEntity<StandardError> genericException(Exception e, HttpServletRequest request) {
        String error = "Internal server error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            e.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}