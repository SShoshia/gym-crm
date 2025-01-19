package com.example.gymcrm.api.rest.error.handler;

import com.example.gymcrm.api.rest.error.dto.ApiErrorResponse;
import com.example.gymcrm.api.rest.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TraineeRegistrationException.class)
    public ResponseEntity<ApiErrorResponse> handleTraineeRegistration(TraineeRegistrationException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TraineeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTraineeNotFound(TraineeNotFoundException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TraineeUpdateException.class)
    public ResponseEntity<ApiErrorResponse> handleTraineeUpdate(TraineeUpdateException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TraineeDeletionException.class)
    public ResponseEntity<ApiErrorResponse> handleTraineeDeletion(TraineeDeletionException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TrainerRegistrationException.class)
    public ResponseEntity<ApiErrorResponse> handleTrainerRegistration(TrainerRegistrationException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TrainerUpdateException.class)
    public ResponseEntity<ApiErrorResponse> handleTrainerUpdate(TrainerUpdateException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                "Validation failed",
                HttpStatus.BAD_REQUEST.value()
        );

        errorResponse.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
