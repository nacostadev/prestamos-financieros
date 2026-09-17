package com.prestamos.exception;

public class BadCredentialsException extends BusinessRuleException {

    public BadCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }
}
