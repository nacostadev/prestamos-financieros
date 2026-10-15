package com.prestamos.exception;

public class ResourceNotFoundException extends BusinessRuleException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}