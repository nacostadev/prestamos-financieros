package com.prestamos.model.prestamo;

import java.math.BigDecimal;

public class PoliticaCredito {

    private String politicaCreditoCodigo;
    private String tiposPrestamoCodigo;
    private BigDecimal politicaCreditoMontoMinimo;
    private BigDecimal politicaCreditoMontoMaximo;
    private String politicaCreditoEstado;

    public PoliticaCredito() {
    }

    public PoliticaCredito(String politicaCreditoCodigo,
                           String tiposPrestamoCodigo,
                           BigDecimal politicaCreditoMontoMinimo,
                           BigDecimal politicaCreditoMontoMaximo,
                           String politicaCreditoEstado) {
        this.politicaCreditoCodigo = politicaCreditoCodigo;
        this.tiposPrestamoCodigo = tiposPrestamoCodigo;
        this.politicaCreditoMontoMinimo = politicaCreditoMontoMinimo;
        this.politicaCreditoMontoMaximo = politicaCreditoMontoMaximo;
        this.politicaCreditoEstado = politicaCreditoEstado;
    }

    public String getPoliticaCreditoCodigo() {
        return politicaCreditoCodigo;
    }

    public void setPoliticaCreditoCodigo(String politicaCreditoCodigo) {
        this.politicaCreditoCodigo = politicaCreditoCodigo;
    }

    public String getTiposPrestamoCodigo() {
        return tiposPrestamoCodigo;
    }

    public void setTiposPrestamoCodigo(String tiposPrestamoCodigo) {
        this.tiposPrestamoCodigo = tiposPrestamoCodigo;
    }

    public BigDecimal getPoliticaCreditoMontoMinimo() {
        return politicaCreditoMontoMinimo;
    }

    public void setPoliticaCreditoMontoMinimo(BigDecimal politicaCreditoMontoMinimo) {
        this.politicaCreditoMontoMinimo = politicaCreditoMontoMinimo;
    }

    public BigDecimal getPoliticaCreditoMontoMaximo() {
        return politicaCreditoMontoMaximo;
    }

    public void setPoliticaCreditoMontoMaximo(BigDecimal politicaCreditoMontoMaximo) {
        this.politicaCreditoMontoMaximo = politicaCreditoMontoMaximo;
    }

    public String getPoliticaCreditoEstado() {
        return politicaCreditoEstado;
    }

    public void setPoliticaCreditoEstado(String politicaCreditoEstado) {
        this.politicaCreditoEstado = politicaCreditoEstado;
    }
}