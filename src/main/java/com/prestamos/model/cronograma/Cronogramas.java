<<<<<<< HEAD
package com.prestamos.model.cronograma;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Cronogramas {

    private String cronogramasCodigo;
    private String prestamosCodigo;
    private BigDecimal cronogramasTEM;
    private BigDecimal cronogramasCuotaFija;
    private String cronogramasEstado;
    private String analistasCodigoGenera;
    private LocalDateTime cronogramasFechaGeneracion;
    private List<DetalleCronograma> detalle;

    public Cronogramas() {
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
        return detalle;
    }

    public void setDetalle(List<DetalleCronograma> detalle) {
        this.detalle = detalle;
    }
}
=======
package com.prestamos.model.cronograma;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cronogramas {

    private String cronogramasCodigo;
    private String prestamosCodigo;
    private BigDecimal cronogramasTEM;
    private BigDecimal cronogramasCuotaFija;
    private String cronogramasEstado;
    private String analistasCodigoGenera;
    private LocalDateTime cronogramasFechaGeneracion;

    public Cronogramas() {
    }

    // getters y setters
}

>>>>>>> 276ab116a50ebe94c66db659cb6e1e84ab77c7ee
