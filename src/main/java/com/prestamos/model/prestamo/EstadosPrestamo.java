package com.prestamos.model.prestamo;

public class EstadosPrestamo {

    private String estadosPrestamoCodigo;
    private String estadosPrestamoNombre;
    private String estadosPrestamoEstado;

    public EstadosPrestamo() {
    }

    public EstadosPrestamo(String estadosPrestamoCodigo,
                           String estadosPrestamoNombre,
                           String estadosPrestamoEstado) {
        this.estadosPrestamoCodigo = estadosPrestamoCodigo;
        this.estadosPrestamoNombre = estadosPrestamoNombre;
        this.estadosPrestamoEstado = estadosPrestamoEstado;
    }

    public String getEstadosPrestamoCodigo() {
        return estadosPrestamoCodigo;
    }

    public void setEstadosPrestamoCodigo(String estadosPrestamoCodigo) {
        this.estadosPrestamoCodigo = estadosPrestamoCodigo;
    }

    public String getEstadosPrestamoNombre() {
        return estadosPrestamoNombre;
    }

    public void setEstadosPrestamoNombre(String estadosPrestamoNombre) {
        this.estadosPrestamoNombre = estadosPrestamoNombre;
    }

    public String getEstadosPrestamoEstado() {
        return estadosPrestamoEstado;
    }

    public void setEstadosPrestamoEstado(String estadosPrestamoEstado) {
        this.estadosPrestamoEstado = estadosPrestamoEstado;
    }
}