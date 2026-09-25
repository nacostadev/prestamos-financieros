package com.prestamos.model.seguridad;

public class Usuarios {

    private String usuariosCodigo;
    private String usuariosNombre;
    private byte[] usuariosContrasenia;
    private String usuariosEstado;
    private String analistasCodigo;

    public Usuarios() {
    }

    public Usuarios(String usuariosCodigo,
                    String usuariosNombre,
                    byte[] usuariosContrasenia,
                    String usuariosEstado,
                    String analistasCodigo) {

        this.usuariosCodigo = usuariosCodigo;
        this.usuariosNombre = usuariosNombre;
        this.usuariosContrasenia = usuariosContrasenia;
        this.usuariosEstado = usuariosEstado;
        this.analistasCodigo = analistasCodigo;
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

    public byte[] getUsuariosContrasenia() {
        return usuariosContrasenia;
    }

    public void setUsuariosContrasenia(byte[] usuariosContrasenia) {
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
}