package com.prestamos.model.cliente;

import java.time.LocalDateTime;

public class HistorialClientes {

    private String historialClientesCodigo;
    private String clientesCodigo;
    private String analistasCodigo;
    private String historialClientesTipoAccion;
    private String historialClientesDescripcion;
    private LocalDateTime historialClientesFecha;

    public HistorialClientes() {
    }

    public HistorialClientes(String historialClientesCodigo,
                             String clientesCodigo,
                             String analistasCodigo,
                             String historialClientesTipoAccion,
                             String historialClientesDescripcion,
                             LocalDateTime historialClientesFecha) {

        this.historialClientesCodigo = historialClientesCodigo;
        this.clientesCodigo = clientesCodigo;
        this.analistasCodigo = analistasCodigo;
        this.historialClientesTipoAccion = historialClientesTipoAccion;
        this.historialClientesDescripcion = historialClientesDescripcion;
        this.historialClientesFecha = historialClientesFecha;
    }

    public String getHistorialClientesCodigo() {
        return historialClientesCodigo;
    }

    public void setHistorialClientesCodigo(String historialClientesCodigo) {
        this.historialClientesCodigo = historialClientesCodigo;
    }

    public String getClientesCodigo() {
        return clientesCodigo;
    }

    public void setClientesCodigo(String clientesCodigo) {
        this.clientesCodigo = clientesCodigo;
    }

    public String getAnalistasCodigo() {
        return analistasCodigo;
    }

    public void setAnalistasCodigo(String analistasCodigo) {
        this.analistasCodigo = analistasCodigo;
    }

    public String getHistorialClientesTipoAccion() {
        return historialClientesTipoAccion;
    }

    public void setHistorialClientesTipoAccion(String historialClientesTipoAccion) {
        this.historialClientesTipoAccion = historialClientesTipoAccion;
    }

    public String getHistorialClientesDescripcion() {
        return historialClientesDescripcion;
    }

    public void setHistorialClientesDescripcion(String historialClientesDescripcion) {
        this.historialClientesDescripcion = historialClientesDescripcion;
    }

    public LocalDateTime getHistorialClientesFecha() {
        return historialClientesFecha;
    }

    public void setHistorialClientesFecha(LocalDateTime historialClientesFecha) {
        this.historialClientesFecha = historialClientesFecha;
    }
}