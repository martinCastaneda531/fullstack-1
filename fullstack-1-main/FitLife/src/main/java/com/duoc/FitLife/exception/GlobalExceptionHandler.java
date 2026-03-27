package com.duoc.FitLife.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoutineNotFoundException.class) 
    public ResponseEntity<ApiError> handlResponseEntity(RoutineNotFoundException ex, HttpServletRequest request) {
        ApiError error = ApiError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value()).error(HttpStatus.NOT_FOUND.getReasonPhrase()).message(ex.getMessage()).path(request.getRequestURI()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<ApiError> handleEndpointNotFound(Exception ex,HttpServletRequest request) {

        ApiError error = ApiError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value()).error(HttpStatus.NOT_FOUND.getReasonPhrase()).message("El endpoint solicitado no existe").path(request.getRequestURI()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> "'" + fe.getField() + "': " + fe.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("La solicitud contiene datos inválidos. Revise el campo 'errors'.")
                .errors(fieldErrors)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException ||
            ex instanceof org.springframework.web.servlet.resource.NoResourceFoundException) {

            ApiError error = ApiError.builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message("El endpoint solicitado no existe")
                    .path(request.getRequestURI())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Ocurrió un error interno inesperado. Por favor, intente más tarde.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleJsonMalformed(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ApiError error = ApiError.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message("El cuerpo de la solicitud (JSON) no es legible o tiene un formato inválido.")
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
