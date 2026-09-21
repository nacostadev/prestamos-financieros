package com.prestamos.model.prestamo;

/**
 *
 * @author LENOVO
 */
public class Garantias {

    private String garantiasCodigo;
    private String garantiasDescripcion;
    private String garantiasEstado;

    public Garantias() {
    }

    public Garantias(String garantiasCodigo,
                     String garantiasDescripcion,
                     String garantiasEstado) {
        this.garantiasCodigo = garantiasCodigo;
        this.garantiasDescripcion = garantiasDescripcion;
        this.garantiasEstado = garantiasEstado;
    }

    public String getGarantiasCodigo() {
        return garantiasCodigo;
    }

    public void setGarantiasCodigo(String garantiasCodigo) {
        this.garantiasCodigo = garantiasCodigo;
    }

    public String getGarantiasDescripcion() {
        return garantiasDescripcion;
    }

    public void setGarantiasDescripcion(String garantiasDescripcion) {
        this.garantiasDescripcion = garantiasDescripcion;
    }

    public String getGarantiasEstado() {
        return garantiasEstado;
    }

    public void setGarantiasEstado(String garantiasEstado) {
        this.garantiasEstado = garantiasEstado;
    }
}