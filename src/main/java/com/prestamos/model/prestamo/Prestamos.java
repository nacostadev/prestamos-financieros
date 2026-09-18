package com.prestamos.model.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamos {

    private String prestamosCodigo;
    private String clientesCodigo;
    private String analistasCodigo;
    private BigDecimal prestamosMontoSolicitado;
    private BigDecimal prestamosMontoAprobado;
    private BigDecimal prestamosTasaInteres;
    private int prestamosPlazoMeses;
    private LocalDate prestamosFechaDesembolso;
    private String prestamosEstado;
    private LocalDateTime prestamosFechaRegistro;

    public Prestamos() {
    }

    public Prestamos(String prestamosCodigo, String clientesCodigo, String analistasCodigo, 
                     BigDecimal prestamosMontoSolicitado, BigDecimal prestamosMontoAprobado, 
                     BigDecimal prestamosTasaInteres, int prestamosPlazoMeses, 
                     LocalDate prestamosFechaDesembolso, String prestamosEstado, 
                     LocalDateTime prestamosFechaRegistro) {
        this.prestamosCodigo = prestamosCodigo;
        this.clientesCodigo = clientesCodigo;
        this.analistasCodigo = analistasCodigo;
        this.prestamosMontoSolicitado = prestamosMontoSolicitado;
        this.prestamosMontoAprobado = prestamosMontoAprobado;
        this.prestamosTasaInteres = prestamosTasaInteres;
        this.prestamosPlazoMeses = prestamosPlazoMeses;
        this.prestamosFechaDesembolso = prestamosFechaDesembolso;
        this.prestamosEstado = prestamosEstado;
        this.prestamosFechaRegistro = prestamosFechaRegistro;
    }

    public String getPrestamosCodigo() { return prestamosCodigo; }
    public void setPrestamosCodigo(String prestamosCodigo) { this.prestamosCodigo = prestamosCodigo; }

    public String getClientesCodigo() { return clientesCodigo; }
    public void setClientesCodigo(String clientesCodigo) { this.clientesCodigo = clientesCodigo; }

    public String getAnalistasCodigo() { return analistasCodigo; }
    public void setAnalistasCodigo(String analistasCodigo) { this.analistasCodigo = analistasCodigo; }

    public BigDecimal getPrestamosMontoSolicitado() { return prestamosMontoSolicitado; }
    public void setPrestamosMontoSolicitado(BigDecimal prestamosMontoSolicitado) { this.prestamosMontoSolicitado = prestamosMontoSolicitado; }

    public BigDecimal getPrestamosMontoAprobado() { return prestamosMontoAprobado; }
    public void setPrestamosMontoAprobado(BigDecimal prestamosMontoAprobado) { this.prestamosMontoAprobado = prestamosMontoAprobado; }

    public BigDecimal getPrestamosTasaInteres() { return prestamosTasaInteres; }
    public void setPrestamosTasaInteres(BigDecimal prestamosTasaInteres) { this.prestamosTasaInteres = prestamosTasaInteres; }

    public int getPrestamosPlazoMeses() { return prestamosPlazoMeses; }
    public void setPrestamosPlazoMeses(int prestamosPlazoMeses) { this.prestamosPlazoMeses = prestamosPlazoMeses; }

    public LocalDate getPrestamosFechaDesembolso() { return prestamosFechaDesembolso; }
    public void setPrestamosFechaDesembolso(LocalDate prestamosFechaDesembolso) { this.prestamosFechaDesembolso = prestamosFechaDesembolso; }

    public String getPrestamosEstado() { return prestamosEstado; }
    public void setPrestamosEstado(String prestamosEstado) { this.prestamosEstado = prestamosEstado; }

    public LocalDateTime getPrestamosFechaRegistro() { return prestamosFechaRegistro; }
    public void setPrestamosFechaRegistro(LocalDateTime prestamosFechaRegistro) { this.prestamosFechaRegistro = prestamosFechaRegistro; }
}