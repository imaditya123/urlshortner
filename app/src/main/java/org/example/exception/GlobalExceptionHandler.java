package org.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.example.model.dto.ApiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Configuration
@RestControllerAdvice
public class GlobalExceptionHandler {

   
    // 1. Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex){
        return ResponseEntity.badRequest().body(ApiResponse.badRequest(ex.getMessage()));
    }

    // 2. Constraints Violation exceptions

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstrainValidation(ConstraintViolationException ex){
        Map<String,String> errors = new HashMap<>();

        for(ConstraintViolation<?> violation : ex.getConstraintViolations()){
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        }
        return ResponseEntity.badRequest().body(ApiResponse.badRequest(ex.getMessage(),errors));
    }
 // 3.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDbError( DataIntegrityViolationException ex){
         return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage(),409));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleDbError( EntityNotFoundException ex){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage(),404));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Void>> handleResponseError( ResponseStatusException ex){
         return ResponseEntity.status(ex.getStatusCode()).body(ApiResponse.error(ex.getReason(),ex.getStatusCode().value()));
    }
    
}
