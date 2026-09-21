package com.prestamos.model.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamos {

    // =========================================================
    // ATRIBUTOS
    // =========================================================
    private String prestamosCodigo;
    private String clientesCodigo;
    private String analistasCodigo;

    private String tiposPrestamoCodigo;
    private BigDecimal prestamosMontoSolicitado;
    private int prestamosPlazoCuotas;
    private BigDecimal prestamosTEA;
    private String modalidadesPagoCodigo;
    private LocalDate prestamosFechaDesembolso;
    private String monedasCodigo;
    private String garantiasCodigo;
    private String prestamosObservaciones;
    private String prestamosDescripcionDestino;

    private BigDecimal prestamosMontoAprobado;
    private BigDecimal prestamosTasaInteres;
    private int prestamosPlazoMeses;
    private String prestamosEstado;
    private LocalDateTime prestamosFechaRegistro;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public Prestamos() {
    }

    // =========================================================
    // GETTERS Y SETTERS
    // =========================================================
    public String getPrestamosCodigo() {
        return prestamosCodigo;
    }

    public void setPrestamosCodigo(String prestamosCodigo) {
        this.prestamosCodigo = prestamosCodigo;
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

    public String getTiposPrestamoCodigo() {
        return tiposPrestamoCodigo;
    }

    public void setTiposPrestamoCodigo(String tiposPrestamoCodigo) {
        this.tiposPrestamoCodigo = tiposPrestamoCodigo;
    }

    public BigDecimal getPrestamosMontoSolicitado() {
        return prestamosMontoSolicitado;
    }

    public void setPrestamosMontoSolicitado(BigDecimal prestamosMontoSolicitado) {
        this.prestamosMontoSolicitado = prestamosMontoSolicitado;
    }

    public int getPrestamosPlazoCuotas() {
        return prestamosPlazoCuotas;
    }

    public void setPrestamosPlazoCuotas(int prestamosPlazoCuotas) {
        this.prestamosPlazoCuotas = prestamosPlazoCuotas;
    }

    public BigDecimal getPrestamosTEA() {
        return prestamosTEA;
    }

    public void setPrestamosTEA(BigDecimal prestamosTEA) {
        this.prestamosTEA = prestamosTEA;
    }

    public String getModalidadesPagoCodigo() {
        return modalidadesPagoCodigo;
    }

    public void setModalidadesPagoCodigo(String modalidadesPagoCodigo) {
        this.modalidadesPagoCodigo = modalidadesPagoCodigo;
    }

    public LocalDate getPrestamosFechaDesembolso() {
        return prestamosFechaDesembolso;
    }

    public void setPrestamosFechaDesembolso(LocalDate prestamosFechaDesembolso) {
        this.prestamosFechaDesembolso = prestamosFechaDesembolso;
    }

    public String getMonedasCodigo() {
        return monedasCodigo;
    }

    public void setMonedasCodigo(String monedasCodigo) {
        this.monedasCodigo = monedasCodigo;
    }

    public String getGarantiasCodigo() {
        return garantiasCodigo;
    }

    public void setGarantiasCodigo(String garantiasCodigo) {
        this.garantiasCodigo = garantiasCodigo;
    }

    public String getPrestamosObservaciones() {
        return prestamosObservaciones;
    }

    public void setPrestamosObservaciones(String prestamosObservaciones) {
        this.prestamosObservaciones = prestamosObservaciones;
    }

    public String getPrestamosDescripcionDestino() {
        return prestamosDescripcionDestino;
    }

    public void setPrestamosDescripcionDestino(String prestamosDescripcionDestino) {
        this.prestamosDescripcionDestino = prestamosDescripcionDestino;
    }

    public BigDecimal getPrestamosMontoAprobado() {
        return prestamosMontoAprobado;
    }

    public void setPrestamosMontoAprobado(BigDecimal prestamosMontoAprobado) {
        this.prestamosMontoAprobado = prestamosMontoAprobado;
    }

    public BigDecimal getPrestamosTasaInteres() {
        return prestamosTasaInteres;
    }

    public void setPrestamosTasaInteres(BigDecimal prestamosTasaInteres) {
        this.prestamosTasaInteres = prestamosTasaInteres;
    }

    public int getPrestamosPlazoMeses() {
        return prestamosPlazoMeses;
    }

    public void setPrestamosPlazoMeses(int prestamosPlazoMeses) {
        this.prestamosPlazoMeses = prestamosPlazoMeses;
    }

    public String getPrestamosEstado() {
        return prestamosEstado;
    }

    public void setPrestamosEstado(String prestamosEstado) {
        this.prestamosEstado = prestamosEstado;
    }

    public LocalDateTime getPrestamosFechaRegistro() {
        return prestamosFechaRegistro;
    }

    public void setPrestamosFechaRegistro(LocalDateTime prestamosFechaRegistro) {
        this.prestamosFechaRegistro = prestamosFechaRegistro;
    }
}
