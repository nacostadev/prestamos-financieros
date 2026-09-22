package com.prestamos.model.cronograma;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cronogramas {

    private String cronogramasCodigo;
    private String prestamosCodigo;
    private BigDecimal cronogramasTEM;
    private BigDecimal cronogramasCuotaFija;
    private String cronogramasEstado;
    private String analistasCodigoGenera;
    private LocalDateTime cronogramasFechaGeneracion;
    private List<DetalleCronograma> detalle = new ArrayList<>();

    public Cronogramas() {
    }

    public Cronogramas(String cronogramasCodigo,
                       String prestamosCodigo,
                       BigDecimal cronogramasTEM,
                       BigDecimal cronogramasCuotaFija,
                       String cronogramasEstado,
                       String analistasCodigoGenera,
                       LocalDateTime cronogramasFechaGeneracion) {
        this.cronogramasCodigo = cronogramasCodigo;
        this.prestamosCodigo = prestamosCodigo;
        this.cronogramasTEM = cronogramasTEM;
        this.cronogramasCuotaFija = cronogramasCuotaFija;
        this.cronogramasEstado = cronogramasEstado;
        this.analistasCodigoGenera = analistasCodigoGenera;
        this.cronogramasFechaGeneracion = cronogramasFechaGeneracion;
    }

    public String getCronogramasCodigo() {
        return cronogramasCodigo;
    }

    public void setCronogramasCodigo(String cronogramasCodigo) {
        this.cronogramasCodigo = cronogramasCodigo;
    }

    public String getPrestamosCodigo() {
        return prestamosCodigo;
    }

    public void setPrestamosCodigo(String prestamosCodigo) {
        this.prestamosCodigo = prestamosCodigo;
    }

    public BigDecimal getCronogramasTEM() {
        return cronogramasTEM;
    }

    public void setCronogramasTEM(BigDecimal cronogramasTEM) {
        this.cronogramasTEM = cronogramasTEM;
    }

    public BigDecimal getCronogramasCuotaFija() {
        return cronogramasCuotaFija;
    }

    public void setCronogramasCuotaFija(BigDecimal cronogramasCuotaFija) {
        this.cronogramasCuotaFija = cronogramasCuotaFija;
    }

    public String getCronogramasEstado() {
        return cronogramasEstado;
    }

    public void setCronogramasEstado(String cronogramasEstado) {
        this.cronogramasEstado = cronogramasEstado;
    }

    public String getAnalistasCodigoGenera() {
        return analistasCodigoGenera;
    }

    public void setAnalistasCodigoGenera(String analistasCodigoGenera) {
        this.analistasCodigoGenera = analistasCodigoGenera;
    }

    public LocalDateTime getCronogramasFechaGeneracion() {
        return cronogramasFechaGeneracion;
    }

    public void setCronogramasFechaGeneracion(LocalDateTime cronogramasFechaGeneracion) {
        this.cronogramasFechaGeneracion = cronogramasFechaGeneracion;
    }

    public List<DetalleCronograma> getDetalle() {
        return detalle != null ? Collections.unmodifiableList(detalle) : Collections.emptyList();
    }

    public void setDetalle(List<DetalleCronograma> detalle) {
        this.detalle = (detalle != null) ? new ArrayList<>(detalle) : new ArrayList<>();
    }

    public void agregarDetalle(DetalleCronograma item) {
        if (item != null) {
            this.detalle.add(item);
        }
    }
}