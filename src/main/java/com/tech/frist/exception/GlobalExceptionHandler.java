package com.tech.frist.exception;

import com.tech.frist.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===============================
    // Validation Errors (400)
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorMessage));
    }

    // ===============================
    // Access Denied (403)
    // ===============================
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error("Access Denied"));
    }

    // ===============================
    // Bad Credentials (401)
    // ===============================
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(
            BadCredentialsException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Invalid email or password"));
    }

    // ===============================
    // Runtime Exceptions (500)
    // ===============================
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntime(
            RuntimeException ex,
            HttpServletRequest request) {

        String path = request.getRequestURI();

        // Don't break Swagger
        if (path.contains("/v3/api-docs") ||
                path.contains("/swagger")) {
            throw ex;
        }

        log.error("Runtime error at {} : {}", path, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Something went wrong"));
    }
}
