package com.prestamos.model.seguridad;

import java.time.LocalDate;

public class DetalleRoles {

    private String rolesCodigo;
    private String usuariosCodigo;
    private LocalDate detalleRolesFechaAsignacion;
    private String detallesRolesEstado;

    public DetalleRoles() {
    }

    public DetalleRoles(String rolesCodigo,
                        String usuariosCodigo,
                        LocalDate detalleRolesFechaAsignacion,
                        String detallesRolesEstado) {

        this.rolesCodigo = rolesCodigo;
        this.usuariosCodigo = usuariosCodigo;
        this.detalleRolesFechaAsignacion = detalleRolesFechaAsignacion;
        this.detallesRolesEstado = detallesRolesEstado;
    }

    public String getRolesCodigo() {
        return rolesCodigo;
    }

    public void setRolesCodigo(String rolesCodigo) {
        this.rolesCodigo = rolesCodigo;
    }

    public String getUsuariosCodigo() {
        return usuariosCodigo;
    }

    public void setUsuariosCodigo(String usuariosCodigo) {
        this.usuariosCodigo = usuariosCodigo;
    }

    public LocalDate getDetalleRolesFechaAsignacion() {
        return detalleRolesFechaAsignacion;
    }

    public void setDetalleRolesFechaAsignacion(LocalDate detalleRolesFechaAsignacion) {
        this.detalleRolesFechaAsignacion = detalleRolesFechaAsignacion;
    }

    public String getDetallesRolesEstado() {
        return detallesRolesEstado;
    }

    public void setDetallesRolesEstado(String detallesRolesEstado) {
        this.detallesRolesEstado = detallesRolesEstado;
    }
}