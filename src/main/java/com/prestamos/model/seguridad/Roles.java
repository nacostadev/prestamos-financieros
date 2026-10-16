package com.prestamos.model.seguridad;

public class Roles {

    private String rolesCodigo;
    private String rolesNombre;

    public Roles() {
    }

    public Roles(String rolesCodigo, String rolesNombre) {

        this.rolesCodigo = rolesCodigo;
        this.rolesNombre = rolesNombre;
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
}
