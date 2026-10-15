package com.prestamos.model.seguridad;

import java.time.LocalDate;

public class Roles {

    private String rolesCodigo;
    private String rolesNombre;
    private String rolesDescripcion;
    private LocalDate rolesFechaCreacion;
    private String rolesEstado;

    public Roles() {
    }

    public Roles(String rolesCodigo, String rolesNombre,
                 String rolesDescripcion, LocalDate rolesFechaCreacion,
                 String rolesEstado) {

        this.rolesCodigo = rolesCodigo;
        this.rolesNombre = rolesNombre;
        this.rolesDescripcion = rolesDescripcion;
        this.rolesFechaCreacion = rolesFechaCreacion;
        this.rolesEstado = rolesEstado;
    }

    public String getRolesCodigo() {
        return rolesCodigo;
    }

    public void setRolesCodigo(String rolesCodigo) {
        this.rolesCodigo = rolesCodigo;
    }

    public String getRolesNombre() {
        return rolesNombre;
    }

    public void setRolesNombre(String rolesNombre) {
        this.rolesNombre = rolesNombre;
    }

    public String getRolesDescripcion() {
        return rolesDescripcion;
    }

    public void setRolesDescripcion(String rolesDescripcion) {
        this.rolesDescripcion = rolesDescripcion;
    }

    public LocalDate getRolesFechaCreacion() {
        return rolesFechaCreacion;
    }

    public void setRolesFechaCreacion(LocalDate rolesFechaCreacion) {
        this.rolesFechaCreacion = rolesFechaCreacion;
    }

    public String getRolesEstado() {
        return rolesEstado;
    }

    public void setRolesEstado(String rolesEstado) {
        this.rolesEstado = rolesEstado;
    }
}