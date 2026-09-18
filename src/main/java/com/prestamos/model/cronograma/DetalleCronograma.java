package com.prestamos.model.cronograma;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalleCronograma {

    private String detalleCronogramaCodigo;
    private String cronogramasCodigo;
    private int detalleNumeroCuota;
    private LocalDate detalleFechaVencimiento;
    private BigDecimal detalleSaldoAnterior;
    private BigDecimal detalleInteres;
    private BigDecimal detalleAmortizacion;
    private BigDecimal detalleCuota;
    private BigDecimal detalleSaldoPendiente;

    public DetalleCronograma() {
    }

    public String getDetalleCronogramaCodigo() {
        return detalleCronogramaCodigo;
    }

    public void setDetalleCronogramaCodigo(String detalleCronogramaCodigo) {
        this.detalleCronogramaCodigo = detalleCronogramaCodigo;
    }

    public String getCronogramasCodigo() {
        return cronogramasCodigo;
    }

    public void setCronogramasCodigo(String cronogramasCodigo) {
        this.cronogramasCodigo = cronogramasCodigo;
    }

    public int getDetalleNumeroCuota() {
        return detalleNumeroCuota;
    }

    public void setDetalleNumeroCuota(int detalleNumeroCuota) {
        this.detalleNumeroCuota = detalleNumeroCuota;
    }

    public LocalDate getDetalleFechaVencimiento() {
        return detalleFechaVencimiento;
    }

    public void setDetalleFechaVencimiento(LocalDate detalleFechaVencimiento) {
        this.detalleFechaVencimiento = detalleFechaVencimiento;
    }

    public BigDecimal getDetalleSaldoAnterior() {
        return detalleSaldoAnterior;
    }

    public void setDetalleSaldoAnterior(BigDecimal detalleSaldoAnterior) {
        this.detalleSaldoAnterior = detalleSaldoAnterior;
    }

    public BigDecimal getDetalleInteres() {
        return detalleInteres;
    }

    public void setDetalleInteres(BigDecimal detalleInteres) {
        this.detalleInteres = detalleInteres;
    }

    public BigDecimal getDetalleAmortizacion() {
        return detalleAmortizacion;
    }

    public void setDetalleAmortizacion(BigDecimal detalleAmortizacion) {
        this.detalleAmortizacion = detalleAmortizacion;
    }

    public BigDecimal getDetalleCuota() {
        return detalleCuota;
    }

    public void setDetalleCuota(BigDecimal detalleCuota) {
        this.detalleCuota = detalleCuota;
    }

    public BigDecimal getDetalleSaldoPendiente() {
        return detalleSaldoPendiente;
    }

    public void setDetalleSaldoPendiente(BigDecimal detalleSaldoPendiente) {
        this.detalleSaldoPendiente = detalleSaldoPendiente;
    }
}