package com.prestamos.model.seguridad;

public class Usuarios {

    private String usuariosCodigo;
    private String usuariosNombre;
    private String usuariosContrasenia;
    private String usuariosEstado;
    private String analistasCodigo;
    private String rolesCodigo;

    public Usuarios() {
    }

    public Usuarios(String usuariosCodigo,
                    String usuariosNombre,
                    String usuariosContrasenia,
                    String usuariosEstado,
                    String analistasCodigo,
                    String rolesCodigo) {

        this.usuariosCodigo = usuariosCodigo;
        this.usuariosNombre = usuariosNombre;
        this.usuariosContrasenia = usuariosContrasenia;
        this.usuariosEstado = usuariosEstado;
        this.analistasCodigo = analistasCodigo;
        this.rolesCodigo = rolesCodigo;
    }

    public String getUsuariosCodigo() {
        return usuariosCodigo;
    }

    public void setUsuariosCodigo(String usuariosCodigo) {
        this.usuariosCodigo = usuariosCodigo;
    }

    public String getUsuariosNombre() {
        return usuariosNombre;
    }

    public void setUsuariosNombre(String usuariosNombre) {
        this.usuariosNombre = usuariosNombre;
    }

    public String getUsuariosContrasenia() {
        return usuariosContrasenia;
    }

    public void setUsuariosContrasenia(String usuariosContrasenia) {
        this.usuariosContrasenia = usuariosContrasenia;
    }

    public String getUsuariosEstado() {
        return usuariosEstado;
    }

    public void setUsuariosEstado(String usuariosEstado) {
        this.usuariosEstado = usuariosEstado;
    }

    public String getAnalistasCodigo() {
        return analistasCodigo;
    }

    public void setAnalistasCodigo(String analistasCodigo) {
        this.analistasCodigo = analistasCodigo;
    }

    public String getRolesCodigo() {
        return rolesCodigo;
    }

    public void setRolesCodigo(String rolesCodigo) {
        this.rolesCodigo = rolesCodigo;
    }
}
