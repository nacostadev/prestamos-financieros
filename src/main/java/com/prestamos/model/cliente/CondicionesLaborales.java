package com.prestamos.model.cliente;

public class CondicionesLaborales {

    private String condicionesLaboralesCodigo;
    private String condicionesLaboralesNombre;
    private String condicionesLaboralesEstado;

    public CondicionesLaborales() {
    }

    public CondicionesLaborales(String condicionesLaboralesCodigo,
                                String condicionesLaboralesNombre,
                                String condicionesLaboralesEstado) {

        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
        this.condicionesLaboralesNombre = condicionesLaboralesNombre;
        this.condicionesLaboralesEstado = condicionesLaboralesEstado;
    }

    public String getCondicionesLaboralesCodigo() {
        return condicionesLaboralesCodigo;
    }

    public void setCondicionesLaboralesCodigo(String condicionesLaboralesCodigo) {
        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
    }

    public String getCondicionesLaboralesNombre() {
        return condicionesLaboralesNombre;
    }

    public void setCondicionesLaboralesNombre(String condicionesLaboralesNombre) {
        this.condicionesLaboralesNombre = condicionesLaboralesNombre;
    }

    public String getCondicionesLaboralesEstado() {
        return condicionesLaboralesEstado;
    }

    public void setCondicionesLaboralesEstado(String condicionesLaboralesEstado) {
        this.condicionesLaboralesEstado = condicionesLaboralesEstado;
    }
}