package com.prestamos.model.cliente;

public class EstadosCiviles {

    private String estadosCivilesCodigo;
    private String estadosCivilesNombre;

    public EstadosCiviles() {
    }

    public EstadosCiviles(String estadosCivilesCodigo, String estadosCivilesNombre) {

        this.estadosCivilesCodigo = estadosCivilesCodigo;
        this.estadosCivilesNombre = estadosCivilesNombre;
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
}
