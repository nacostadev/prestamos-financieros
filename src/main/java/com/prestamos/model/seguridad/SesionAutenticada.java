package com.prestamos.model.seguridad;

import java.util.Collections;
import java.util.List;

public final class SesionAutenticada {

    private final String usuarioCodigo;
    private final String username;
    private final String analistaCodigo;
    private final List<Roles> roles;

    public SesionAutenticada(String usuarioCodigo, String username,
                             String analistaCodigo, List<Roles> roles) {
        this.usuarioCodigo = usuarioCodigo;
        this.username = username;
        this.analistaCodigo = analistaCodigo;
        this.roles = Collections.unmodifiableList(roles);
    }

    public String getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public String getUsername() {
        return username;
    }

    public String getAnalistaCodigo() {
        return analistaCodigo;
    }

    public List<Roles> getRoles() {
        return roles;
    }
}