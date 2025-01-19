package com.example.gymcrm.api.rest.error.exception;

public class TraineeNotFoundException extends RuntimeException {
    public TraineeNotFoundException(String message) {
        super(message);
    }
}
