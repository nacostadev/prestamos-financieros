package com.prestamos.exception;

public class UserDisabledException extends BusinessRuleException {

    public UserDisabledException() {
        super("La cuenta del usuario está inactiva");
    }
}
