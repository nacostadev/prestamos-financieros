package com.prestamos.model.prestamo;

import java.math.BigDecimal;

/**
 *
 * @author LENOVO
 */
public class TiposPrestamo {

    private String tiposPrestamoCodigo;
    private String tiposPrestamoNombre;
    private int tiposPrestamoPlazoMinimo;
    private int tiposPrestamoPlazoMaximo;
    private BigDecimal tiposPrestamoTasaMinima;
    private BigDecimal tiposPrestamoTasaMaxima;
    private String tiposPrestamoEstado;

    public TiposPrestamo() {
    }

    public TiposPrestamo(String tiposPrestamoCodigo,
                         String tiposPrestamoNombre,
                         int tiposPrestamoPlazoMinimo,
                         int tiposPrestamoPlazoMaximo,
                         BigDecimal tiposPrestamoTasaMinima,
                         BigDecimal tiposPrestamoTasaMaxima,
                         String tiposPrestamoEstado) {
        this.tiposPrestamoCodigo = tiposPrestamoCodigo;
        this.tiposPrestamoNombre = tiposPrestamoNombre;
        this.tiposPrestamoPlazoMinimo = tiposPrestamoPlazoMinimo;
        this.tiposPrestamoPlazoMaximo = tiposPrestamoPlazoMaximo;
        this.tiposPrestamoTasaMinima = tiposPrestamoTasaMinima;
        this.tiposPrestamoTasaMaxima = tiposPrestamoTasaMaxima;
        this.tiposPrestamoEstado = tiposPrestamoEstado;
    }

    public String getTiposPrestamoCodigo() {
        return tiposPrestamoCodigo;
    }

    public void setTiposPrestamoCodigo(String tiposPrestamoCodigo) {
        this.tiposPrestamoCodigo = tiposPrestamoCodigo;
    }

    public String getTiposPrestamoNombre() {
        return tiposPrestamoNombre;
    }

    public void setTiposPrestamoNombre(String tiposPrestamoNombre) {
        this.tiposPrestamoNombre = tiposPrestamoNombre;
    }

    public int getTiposPrestamoPlazoMinimo() {
        return tiposPrestamoPlazoMinimo;
    }

    public void setTiposPrestamoPlazoMinimo(int tiposPrestamoPlazoMinimo) {
        this.tiposPrestamoPlazoMinimo = tiposPrestamoPlazoMinimo;
    }

    public int getTiposPrestamoPlazoMaximo() {
        return tiposPrestamoPlazoMaximo;
    }

    public void setTiposPrestamoPlazoMaximo(int tiposPrestamoPlazoMaximo) {
        this.tiposPrestamoPlazoMaximo = tiposPrestamoPlazoMaximo;
    }

    public BigDecimal getTiposPrestamoTasaMinima() {
        return tiposPrestamoTasaMinima;
    }

    public void setTiposPrestamoTasaMinima(BigDecimal tiposPrestamoTasaMinima) {
        this.tiposPrestamoTasaMinima = tiposPrestamoTasaMinima;
    }

    public BigDecimal getTiposPrestamoTasaMaxima() {
        return tiposPrestamoTasaMaxima;
    }

    public void setTiposPrestamoTasaMaxima(BigDecimal tiposPrestamoTasaMaxima) {
        this.tiposPrestamoTasaMaxima = tiposPrestamoTasaMaxima;
    }

    public String getTiposPrestamoEstado() {
        return tiposPrestamoEstado;
    }

    public void setTiposPrestamoEstado(String tiposPrestamoEstado) {
        this.tiposPrestamoEstado = tiposPrestamoEstado;
    }
}