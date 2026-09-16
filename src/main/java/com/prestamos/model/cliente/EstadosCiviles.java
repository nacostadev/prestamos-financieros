package com.prestamos.model.cliente;

public class EstadosCiviles {

    private String estadosCivilesCodigo;
    private String estadosCivilesNombre;
    private String estadosCivilesEstado;

    public EstadosCiviles() {
    }

    public EstadosCiviles(String estadosCivilesCodigo,
                          String estadosCivilesNombre,
                          String estadosCivilesEstado) {

        this.estadosCivilesCodigo = estadosCivilesCodigo;
        this.estadosCivilesNombre = estadosCivilesNombre;
        this.estadosCivilesEstado = estadosCivilesEstado;
    }

    public String getEstadosCivilesCodigo() {
        return estadosCivilesCodigo;
    }

    public void setEstadosCivilesCodigo(String estadosCivilesCodigo) {
        this.estadosCivilesCodigo = estadosCivilesCodigo;
    }

    public String getEstadosCivilesNombre() {
        return estadosCivilesNombre;
    }

    public void setEstadosCivilesNombre(String estadosCivilesNombre) {
        this.estadosCivilesNombre = estadosCivilesNombre;
    }

    public String getEstadosCivilesEstado() {
        return estadosCivilesEstado;
    }

    public void setEstadosCivilesEstado(String estadosCivilesEstado) {
        this.estadosCivilesEstado = estadosCivilesEstado;
    }
}